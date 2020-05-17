import Repository from "./Repository";

const resource = "/categories";

const categoryRepository = Repository;
// categoryRepository.defaults.headers.common["Authorization"] = "Bearer " + window.localStorage.getItem("token");

export default {
  fetchCategories(){
    return categoryRepository.get(`${resource}`);
  }
};
