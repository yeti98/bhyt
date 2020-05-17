import Repository from "./Repository";

const resource = "/settings";

const settingRepository = Repository;
// settingRepository.defaults.headers.common["Authorization"] = "Bearer " + window.localStorage.getItem("token");

export default {
  fetchSettings(){
    return settingRepository.get(`${resource}`);
  }
};
