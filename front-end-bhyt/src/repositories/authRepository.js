import Repository from "./Repository";

const resource = "/auth";
export default {
  signupFacebook(token) {
    return Repository.post(`${resource}/signup/facebook`, token);
  },
  loginFacebook(token) {
    return Repository.post(`${resource}/login/facebook`, token);
  },

  signup(payload) {
    return Repository.post(`${resource}/signup`, payload);
  },

  login(payload) {
    return Repository.post(`${resource}/login`, payload);
  },
  changepass(changepassRequest){
    return Repository.post(`${resource}/changepassword`, changepassRequest);
  },
  resetpassword(email){
    return Repository.post(`${resource}/resetpassword`, email);
  },
  savenewpass(passRequest){
    return Repository.post(`${resource}/resetpass`, passRequest);
  },
  verify(token){
    return Repository.get(`${resource}/verify/${token}`);
  }
};
