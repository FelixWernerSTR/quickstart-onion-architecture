<template>
  <div class="worker-edit">
    <nav>
      <RouterLink to="/">Worker</RouterLink>&nbsp;&nbsp;&nbsp;
      <RouterLink to="/work-log">WorkLog</RouterLink>&nbsp;&nbsp;&nbsp;
      <RouterLink to="/about">About</RouterLink>
    </nav>
    <h1>Create/Update Worker</h1>
    
    <!-- Search field -->
    <div class="search-container">
      <input v-model="searchLogin" @input="searchWorker" placeholder="Search by login" />
      <button @click="getWorkerForLogin()">Search Worker</button>
    </div>

    <!-- Edit form -->
      <div class="form-group">
        <label for="login">Login:</label>
        <input id="login" v-model="worker.login" required />
      </div>

      <div v-if="v$.login.$anyDirty && v$.login.$invalid">
        <small class="form-text text-danger" v-for="error of v$.login.$errors" :key="error.$uid">{{ error.$message }}</small>
      </div>

      <div class="form-group">
        <label for="name">Name:</label>
        <input id="name" v-model="worker.name" required />
      </div>
      
      <div v-if="v$.name.$anyDirty && v$.name.$invalid">
        <small class="form-text text-danger" v-for="error of v$.name.$errors" :key="error.$uid">{{ error.$message }}</small>
      </div>

      <div class="form-group">
        <label for="surname">Surname:</label>
        <input id="surname" v-model="worker.surname" required />
      </div>
      
      <div v-if="v$.surname.$anyDirty && v$.surname.$invalid">
        <small class="form-text text-danger" v-for="error of v$.surname.$errors" :key="error.$uid">{{ error.$message }}</small>
      </div>

      <div class="form-group">
        <label for="email">Email:</label>
        <input id="email" v-model="worker.email" type="email" required />
      </div>
      
      <div v-if="v$.email.$anyDirty && v$.email.$invalid">
        <small class="form-text text-danger" v-for="error of v$.email.$errors" :key="error.$uid">{{ error.$message }}</small>
      </div>
      <br/>
      <button v-on:click="save()">Create Worker</button>
      <button v-on:click="update()">Update Worker</button>
      <button v-on:click="clearFields()">Clear Fields</button>
    
  </div>
</template>

<script lang="ts" src="./Worker.ts"></script>