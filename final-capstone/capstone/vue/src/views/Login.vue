<template>
<div>
  <div id="login">
    <div class="image"> 
      <div class="word-bubble">
          <p class="bubble-text">No Guest User Can Defeat Doom!</p> 

      </div>
    </div>
    <form @submit.prevent="login">
      <h1 >Please Sign In</h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username">Username </label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password </label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <button type="submit" class="signin">Sign In</button>
      <p>
      <router-link :to="{ name: 'register' }" class="register-link">Need an account? Sign up.</router-link></p>
    </form>
    <div class="image2"> </div>
  </div>
  <div class = "bottom-image">

  </div>
</div>
   
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
}
input {
  font-size: 15px;
 
}
label {
  margin-right: 0.5rem;
}
a.register-link {
  rucolor: gold;
}
.signin {
  width: 30%;
  font-family: 'Bangers', cursive;
  font-size: 25px;
}
form{
  font-size: 30px;
  width: 31%;
  border: 2px solid gray; /* Add border */
  border-radius: 10px; /* Optional: Add border radius for rounded corners */
  padding: 10px;
  background-color: rgba(150, 150, 150, 0.568);
  z-index: 1;
}
#login {
  display: flex;
  flex-direction: space-between;
  position: relative;
  text-align: center;
  font-family: 'Bangers', cursive;
  height: 50vh;
  width: 100%;
}
.image {
  background-image: url('../../public/doom.png');
  height: 100%;
  width: 33%;
  background-repeat: no-repeat;
    background-position: top center;
    background-size: 50%;
     position: relative;
}
.word-bubble {
  position: absolute;
  top:-10%; 
  left: 60%; 
  background-image: url('../../public/wordbubble.png');
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
  width: 200px; 
  height: 150px; 
}
.bubble-text {
  font-size: 18px; 
  text-align: center;
  margin: 0;
  padding: 40px; 
  color: black; 

}
.image2 {
  background-image: url('../../public/ultron.png');
  height: 100%;
  width: 33%;
  background-repeat: no-repeat;
  background-position: top center;
  background-size: 40%;
}
.bottom-image {
  background-image: url('../../public/crimsondynamo.png');
  height: 50vh;
  width: 100%;
  background-repeat: no-repeat;
  background-position: bottom center;
  background-size: 25%;
}
  @media only screen and (max-width: 600px) {
    .image {
      background-size: contain;
    }
    .image2 {
      background-size: contain;
    }
    .bottom-image {
      background-size: contain;
    }
    input {
       width: 90%;
    }
    .signin{
      width: 50%;
    }
  }


</style>