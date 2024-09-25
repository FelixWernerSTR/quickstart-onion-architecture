import { defineComponent, ref, watch } from "vue";
import { useRouter } from "vue-router";
import { storeToRefs } from "pinia";
import { useWorkerWorkLog } from "@/stores/WorkerWorkLogStore";
import { useVuelidate } from '@vuelidate/core'
import { maxLength } from '@vuelidate/validators'
import { Worker } from "@/model/Worker.model";
import { WorkLog } from "@/model/WorkLog.model";
import { useI18n } from 'vue-i18n';
import { useValidation } from '@/config/validation';

export default defineComponent({
  name: "WorkLog",
  components: {
  },
  setup() { // do not use the keyword 'this' in the setup method -> 'this' is undefined in the setup method
    const workerWorkLogStore = useWorkerWorkLog();
    const { worklog, worker, isRequestLoading, searchLogin, worklogs } = storeToRefs( workerWorkLogStore );

    function save() {
      console.debug("worklog:", worklog.value);
      workerWorkLogStore.saveWorkLog();
    }
    
    function update() {
      workerWorkLogStore.updateWorkLog();
    }
    
    function clearFileds() {
      workerWorkLogStore.clearWorkLog();
    }
    
    function getWorkerForLogin() {
      console.debug("getWorkerForLogin:", searchLogin.value);
      workerWorkLogStore.getWorkerForLogin();
      workerWorkLogStore.getWorkLogsForLogin();
    }

    //https://runthatline.com/pinia-watch-state-getters-inside-vue-components/
    watch(worker.value, () => {
      console.debug('worker ref changed, do something!')
    })
    
    //https://runthatline.com/pinia-watch-state-getters-inside-vue-components/
    watch(worklog.value, () => {
      console.debug('worklog ref changed, do something!')
    })


    
    //https://vuelidate-next.netlify.app/
    //https://lokalise.com/blog/vue-i18n/
    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      associatedWorkerLogin: {
        required: validations.required(t$('entity.validation.required')),
      },
      description: {
        required: validations.required(t$('entity.validation.required')),
      },
      timeSpentInSeconds: {
        required: validations.required(t$('entity.validation.required')),
      },
      startDate: {
        required: validations.required(t$('entity.validation.required')),
      },
    }

    let v$ = useVuelidate(validationRules, worklog.value)
    v$.value.$validate();

    watch(v$, () => {
      console.debug('v$ changed, do something!', v$)
      if(v$.value.$errors.length>0){
         console.debug('v$.$errors', v$.value.$errors);
         workerWorkLogStore.setIsWorkerValid(false);
      }else{
         workerWorkLogStore.setIsWorkerValid(true);
      }
    });

    return {
      worker,
      searchLogin,
      worklog,
      worklogs,
      save,
      update,
      getWorkerForLogin,
      clearFileds,
      v$,
      t$,
    };
  },
});
