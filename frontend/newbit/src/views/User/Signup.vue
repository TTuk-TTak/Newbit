<template>
  <v-container class="fill-height">
    <v-row
      align="center"
      justify="center"
      dense
    >
      <v-col cols="5">
        <v-card
          class="rounded-xl"
          elevation="3"
          height="700"
        >
          <v-row justify="center">
            <v-col cols="10">
              <v-card-title class="d-flex justify-center mb-3">
                <span class="text-h3 font-weight-bold">Newbit</span>
              </v-card-title>
              <v-card-text>
                <v-form
                  ref="form"
                  lazy-validation
                >
                  <!-- vuetify validation 방법 사용 v-text-field에 rules를 지정해 넘긴다. -->
                  <v-text-field
                    v-model.trim="credentials.userNick"
                    :rules="userNick_rule"
                    label="닉네임"
                    type="text"
                    solo
                    outlined
                    rounded
                    required
                  ></v-text-field>
                  <!-- rules에 data의 userId rule 과 id중복 computed 속성 넘겨준다.-->
                  <v-text-field
                    v-model.trim="credentials.userId"
                    :rules="[...userId_rule, idConfirmationRule]"
                    label="아이디"
                    type="text"
                    solo
                    outlined
                    rounded
                  ></v-text-field>
                  <v-text-field
                    v-model.trim="credentials.userEmail"
                    :rules="[...email_rule, emailConfirmationRule]"
                    label="이메일"
                    type="email"
                    solo
                    outlined
                    rounded
                  ></v-text-field>
                  <v-text-field
                    v-model.trim="credentials.userPassword"
                    :rules="password_rule"
                    label="비밀번호"
                    type="password"
                    solo
                    outlined
                    rounded
                  ></v-text-field>
                  <!-- rules에 비밀번호 체크 computed 속성 넘겨줌 -->
                  <v-text-field
                    v-model.trim="rePassword"
                    :rules="[passwordConfirmationRule]"
                    class="mb-5"
                    label="비밀번호 확인"
                    type="password"
                    solo
                    outlined
                    rounded
                    @keypress.enter="signup(credentials)"
                  ></v-text-field>
                  <v-btn
                    @click="signup(credentials)"
                    class="font-weight-bold"
                    color="black"
                    dark
                    x-large
                    block
                    rounded
                  >가입하기</v-btn>
                  <v-card-actions class="d-flex justify-center mt-6 pa-0">
                    <span class="text-h7 grey--text mr-1">이미 계정이 있으신가요?</span>
                    <router-link :to="{ name: 'Login' }">로그인 하기</router-link>
                  </v-card-actions>
                </v-form>
              </v-card-text>
            </v-col>
          </v-row>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Signup',
  data: function () {
    return {
      credentials: {
        userNick: '',
        userId: '',
        userEmail: '',
        userPassword: '',
      },
      rePassword: '',
      emailCheck: '',
      idCheck: '',
      // 유효성 검사
      userNick_rule: [
        value => !!value || '필수항목입니다.',
        value => {
          const pattern2 = /^[a-zA-Z0-9가-힣]*$/ // 아이디를 한글로 ㄱㅁ 입력해도 경고 메세지가 뜨지 않음!
          return pattern2.test(value) || '닉네임은 영문+숫자+한글만 입력 가능합니다.'
        },
        value => 2 <= value?.length || '닉네임은 최소 2자이상 입니다.',
        value => value?.length <= 12 || '닉네임은 최대 12자이하 입니다.',
      ],
      userId_rule: [
        value => !!value || '필수항목입니다.',
        value => {
          const pattern1 = /^[a-zA-Z0-9]*$/
          return pattern1.test(value) || '아이디는 영문+숫자만 입력 가능합니다.'
        },
        value => 4 <= value?.length || '아이디는 최소 4자이상 입니다.',
        value => value?.length <= 10 || '아이디는 최대 10자이하 입니다.',
      ],
      email_rule: [
        value => !!value || '필수항목입니다.',
        value => {
          const pattern4 = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
          return pattern4.test(value) || '잘못된 이메일 형식입니다.'
        },
      ],
      password_rule: [
        value => !!value || '필수항목입니다.',
        value => {
          const pattern3 = /^[a-zA-Z0-9!@#$%^&*]*$/
          return pattern3.test(value) || '비밀번호는 영문+숫자+특수문자만 입력 가능합니다.'
        },
        value => 8 <= value?.length || '비밀번호는 최소 8자이상 입니다.',
        value => value?.length <= 15 || '비밀번호는 최대 15자이하 입니다.',
      ],
    }
  },
  methods: {
    signup (credentials) {
      const validate = this.$refs.form.validate()
      if (validate) {
        axios.post(`${this.$serverURL}/user/signup`, credentials)
          .then(() => {
            this.$goToSignupCompletePage()
          })
          .catch((err) => {
            console.log(err)
          })

      }
    },
    // API 요청을 보내서 회신 데이터 유무에 따라 data의 idCheck 값을 바꾼다.
    checkId (user_id) {
      axios.post(`${this.$serverURL}/user/signup/idCheck`, { userId: user_id })
        .then((res) => {
          if (res.data === 'fail') {
            this.idCheck = false
          }
          else {
            this.idCheck = true
          }
        })
    },
    checkEmail (user_email) {
      axios.post(`${this.$serverURL}/user/signup/emailCheck`, { userEmail: user_email })
        .then((res) => {
          if (res.data === 'fail') {
            this.emailCheck = false
          }
          else {
            this.emailCheck = true
          }
        })
    }
  },
  // data 객체의 변화를 감지해서 위의 checkId 실행
  watch: {
    credentials: {
      handler: function (value) {
        this.checkId(value['userId'])
        this.checkEmail(value['userEmail'])
      },
      deep: true
    }
  },
  computed: {
    // 비밀번호와 비밀번호 확인이 틀린 걸 계산된 속성으로 v-text-field rules에 추가해서 넘겨줌
    passwordConfirmationRule () {
      return () => (this.credentials.userPassword === this.rePassword) || '비밀번호가 맞지 않습니다.'
    },
    // 위에서 변화하는 idCheck를 기준으로 아이디 체크 규칙을 계산된 속성으로 만든다.
    idConfirmationRule () {
      return () => (this.idCheck) || '이미 사용중인 아이디입니다.'
    },
    emailConfirmationRule () {
      return () => (this.emailCheck) || '이미 사용중인 이메일입니다.'
    }
  }
}
</script>

<style>
</style>