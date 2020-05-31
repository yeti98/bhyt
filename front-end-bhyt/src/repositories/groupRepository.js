import Repository from "./Repository";

const resource = "/categories";

const categoryRepository = Repository;
// categoryRepository.defaults.headers.common["Authorization"] = "Bearer " + window.localStorage.getItem("token");

export default {
  fetchCategories(){
    return categoryRepository.get(`${resource}`);
  },
  insertCategory(category){
    return categoryRepository.post(`${resource}/`, category);
  },
  updateCategory(cateID, category){
    return categoryRepository.put(`${resource}/${cateID}`, category);
  },
  deleteCategory(cateID){
    return categoryRepository.delete(`${resource}/${cateID}`);
  },
};
