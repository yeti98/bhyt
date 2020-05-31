import Repository from "./Repository";

const resource = "/users";
export default {
  signup(payload) {
    return Repository.post(`${resource}/signup`, payload);
  },

  login(payload) {
    return Repository.post(`${resource}/login`, payload);
  }
};
