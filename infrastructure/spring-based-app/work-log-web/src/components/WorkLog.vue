<template>
  <div class="worklog-edit">
    <nav>
      <RouterLink to="/">Worker</RouterLink>&nbsp;&nbsp;&nbsp;
      <RouterLink to="/work-log">WorkLog</RouterLink>&nbsp;&nbsp;&nbsp;
      <RouterLink to="/about">About</RouterLink>
    </nav>
    <h1>Create/Update WorkLog</h1>
    <!-- Search field -->
    <div class="search-container">
      <input v-model="searchLogin" placeholder="Search by login" />
      <button @click="getWorkerForLogin()">Search Worker/Update WorkLog-List</button>
    </div>

    <!-- Edit form -->
    
      <div class="form-group">
        <label for="login">Associated Worker Login:</label>
        <input id="login" v-model="worker.login" required />
      </div>
  
      <div class="form-group">
        <label for="workLogId">Worklog ID:</label>
        <input id="workLogId" v-model.number="worklog.workLogId" type="number" readonly />
      </div>

      <div class="form-group">
        <label for="startDate">Start Date:</label>
        <input id="startDate" v-model="worklog.startDate" placeholder="YYYY-MM-DD" type="datetime" required />
      </div>
      
      <div v-if="v$.startDate.$anyDirty && v$.startDate.$invalid">
        <small class="form-text text-danger" v-for="error of v$.startDate.$errors" :key="error.$uid">{{ error.$message }}</small>
      </div>

      <div class="form-group">
        <label for="timeSpent">Time Spent (in seconds):</label>
        <input id="timeSpent" v-model.number="worklog.timeSpentInSeconds" type="number" required />
      </div>
      
      <div v-if="v$.timeSpentInSeconds.$anyDirty && v$.timeSpentInSeconds.$invalid">
        <small class="form-text text-danger" v-for="error of v$.timeSpentInSeconds.$errors" :key="error.$uid">{{ error.$message }}</small>
      </div>


      <div class="form-group">
        <label for="description">Description:</label>
        <textarea id="description" v-model="worklog.description" rows="4" required></textarea>
      </div>
      
      <div v-if="v$.description.$anyDirty && v$.description.$invalid">
        <small class="form-text text-danger" v-for="error of v$.description.$errors" :key="error.$uid">{{ error.$message }}</small>
      </div>

      <button v-on:click="save()">Add WorkLog</button> 
      <button v-on:click="update()">Update WorkLog</button> 
      <button v-on:click="clearFileds()">Clear Fields</button>
      
      <div class="work-log-list">
         <h2>WorkLogs for Worker ({{ worker.login }})</h2>
         <table>
           <thead>
             <tr>
               <th>ID</th>
               <th>Start Date</th>
               <th>Time Spent</th>
               <th>Description</th>
             </tr>
           </thead>
           <tbody>
             <tr v-for="workLog in worklogs" :key="workLog.workLogId">
               <td>{{ workLog.workLogId }}</td>
               <td>{{ workLog.startDate }}</td>
               <td>{{ workLog.timeSpentInSeconds }}</td>
               <td>{{ workLog.description }}</td>
             </tr>
           </tbody>
         </table>
       </div>
  </div>
</template>

<script lang="ts" src="./WorkLog.ts"></script>