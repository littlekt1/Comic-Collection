<template>
  <div id="register" class="text-center">
        <div class="image" :class="{'image-premium': user.tier === 'premium'}">
  <div class="word-bubble">
    <p class="bubble-text">Check out my collection after signing up. You trust me with your credit card, surely.</p> 
  </div>
</div>
    <form @submit.prevent="register">
      <h1>Create Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
         <div class="form-input-group">
        <label for="tier">Tier</label>
        <div>
          <label>
            <input type="radio" v-model="user.tier" value="standard" required /> Standard
          </label>
        </div>
        <div>
          <label>
            <input type="radio" v-model="user.tier" value="premium" required /> Premium 
          </label>
        </div>
      </div>
      <div v-if="user.tier === 'premium'">
        <div class="form-input-group">
          <label for="paymentMethod">Payment Method</label>
          <select id="paymentMethod" v-model="user.paymentMethod" >
            <option value="credit_card">Credit Card</option>
          </select>
        </div>
        <div class="form-input-group">
          <label for="cardNumber">Card Number</label>
          
          <input type="text" id="cardNumber" v-model="user.cardNumber" />
        </div>
        <h7>*-All Donations Go Towards Comics!-* </h7>
        <div class="form-input-group">
          <label for="securityNumber">Security Number (CVV)</label>
          <input type="text" id="securityNumber" v-model="user.securityNumber" />
        </div>
        <div class="form-input-group">
          <label for="billingAddress">Billing Address</label>
          <input type="text" id="billingAddress" v-model="user.billingAddress"  />
        </div>
        <div class="form-input-group">
          <label for="zipCode">Zip Code</label>
          <input type="text" id="zipCode" v-model="user.zipCode" />
        </div>
        <div class="form-input-group">
          <label for="cost">Cost</label>
          <input type="text" id="cost" v-model="user.cost" :readonly="true" value="$4.99" />
        </div>
      </div>
      <button type="submit" class="button">Create Account</button>
      <p><router-link :to="{ name: 'login' }" class="register-link">Already have an account? Log in.</router-link></p>
    </form>
          <div class="image2"> 
</div>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
        tier: 'standard',
        paymentMethod: 'credit_card',
        cardNumber: '',
        securityNumber: '',
        billingAddress: '',
        zipCode: '',
        cost: '$4.99', 
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>

#register {
  display: flex;
  align-items: center;
  justify-content: flex-start; 
  height: 90vh;
  font-family: 'Bangers', cursive;
  text-align: center;
  position: relative;

  }
a.register-link {
  color: gold;
}
.form-input-group {
  margin-bottom: 0.5rem; 
}
form{
  font-size: 30px;
  width: 25%;
  border: 2px solid gray; /* Add border */
  border-radius: 10px; /* Optional: Add border radius for rounded corners */
  padding: 10px;
  background-color: rgba(150, 150, 150, 0.568);
  z-index: 1;
}

label {
  margin-right: 0.5rem;
}
.button {
  width: 100%;
  font-family: 'Bangers', cursive;
  font-size: 25px;
}
.image {
  background-image: url('../assets/galactus.png');
  height: 100%;
  width: 33%;
  background-size: contain;
  background-position: bottom;
  background-repeat: no-repeat;
  scale: 1;
}
/*  .image-premium {
  background-image: url('../assets/galactus.png');
  background-repeat: no-repeat;
  background-position: bottom left;
  background-size: contain;
  position: relative; 
} */
.word-bubble {
  position: absolute;
  top:170px; 
  left: 80px; 
  background-image: url('../assets/wordbubble2.png');
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
  width: 200px; 
  height: 150px; 
  scale: 1.3;
}
.bubble-text {
  font-size: 14px; 
  text-align: center;
  margin: 0;
  padding: 35px; 
  color: black; 
  
}
.image2 {
  background-image: url('../assets/galactuspanel.jpg');
  height: 100%;
  width: 33%;
  background-size: contain;
  background-repeat: no-repeat;
  background-position: top right;
  margin: 20px;

  scale: 1;
}

@media only screen and (max-width: 600px) {
    input {
       width: 90%;
    }
    .signin{
      width: 50%;
  }
    form {
    width: 90%;
    margin: 0 auto;
  }
    .image,
  .image2 {
    display: none;
  }
}

</style>
