<template>
  <div id="register" class="text-center">
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
            <input type="radio" v-model="user.tier" value="premium" /> Premium
          </label>
        </div>
      </div>
      <div v-if="user.tier === 'premium'">
        <div class="form-input-group">
          <label for="paymentMethod">Payment Method</label>
          <select id="paymentMethod" v-model="user.paymentMethod" required>
            <option value="credit_card">Credit Card</option>
          </select>
        </div>
        <div class="form-input-group">
          <label for="cardNumber">Card Number</label>
          <input type="text" id="cardNumber" v-model="user.cardNumber" required />
        </div>
        <div class="form-input-group">
          <label for="securityNumber">Security Number (CVV)</label>
          <input type="text" id="securityNumber" v-model="user.securityNumber" required />
        </div>
        <div class="form-input-group">
          <label for="billingAddress">Billing Address</label>
          <input type="text" id="billingAddress" v-model="user.billingAddress" required />
        </div>
        <div class="form-input-group">
          <label for="zipCode">Zip Code</label>
          <input type="text" id="zipCode" v-model="user.zipCode" required />
        </div>
        <div class="form-input-group">
          <label for="cost">Cost</label>
          <input type="text" id="cost" v-model="user.cost" :readonly="true" value="$4.99" />
        </div>
      </div>
      <button type="submit">Create Account</button>
      <p><router-link :to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
    </form>
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
  flex-direction: column;
  align-items: center;
  justify-content: flex-start; 
  height: 100vh;
  }

.form-input-group {
  margin-bottom: 0.5rem; 
}

label {
  margin-right: 0.5rem;
}

h1 {
  font-family: 'Bangers', cursive; 
}

</style>
