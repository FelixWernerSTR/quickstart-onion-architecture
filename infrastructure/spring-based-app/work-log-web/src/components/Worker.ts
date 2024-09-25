import { defineComponent, ref, watch } from "vue";
import { storeToRefs } from "pinia";
import { useWorkerWorkLog} from "@/stores/WorkerWorkLogStore";
import { useVuelidate } from '@vuelidate/core'
import { useI18n } from 'vue-i18n';
import { useValidation } from '@/config/validation';

export default defineComponent({
  name: "Worker",
  components: {
  },
  setup() {
    const workerWorkLogStore = useWorkerWorkLog();
    const { worker, searchLogin } = storeToRefs(workerWorkLogStore);

    function save() {
      console.debug("worker:", worker.value);
      workerWorkLogStore.saveWorker();
    }
    
    function update() {
      console.debug("worker:", worker.value);
      workerWorkLogStore.updateWorker();
    }
    
    function clearFields() {
      workerWorkLogStore.clearWorker();
    }
    
    function getWorkerForLogin() {
      console.log("getWorkerForLogin:", searchLogin);
      workerWorkLogStore.getWorkerForLogin(searchLogin);
    }

    //https://runthatline.com/pinia-watch-state-getters-inside-vue-components/
    watch(worker.value, () => {
      console.debug('Worker ref changed, do something!')
    })

    
    //https://vuelidate-next.netlify.app/
    //https://lokalise.com/blog/vue-i18n/
    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      login: {
        required: validations.required(t$('entity.validation.required')),
      },
      name: {
        required: validations.required(t$('entity.validation.required')),
      },
      surname: {
        required: validations.required(t$('entity.validation.required')),
      },
      email: {
        required: validations.required(t$('entity.validation.required')),
      },
    }

    let v$ = useVuelidate(validationRules, worker.value)
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
      clearFields,
      save,
      update,
      getWorkerForLogin,
      v$,
      t$,
    };
  },
});
