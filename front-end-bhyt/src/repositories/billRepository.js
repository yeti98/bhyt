import Repository from "./Repository";

const resource = "/bills";

const billRepository = Repository;
// billRepository.defaults.headers.common["Authorization"] = "Bearer " + window.localStorage.getItem("token");

export default {
  getAllBills(){
    return billRepository.get(`${resource}`);
  },
  getCategoryByBillID(id){
    return billRepository.get(`${resource}/${id}/category`)
  },
  analyzeBills(startDate, endDate){
    return billRepository.get(`${resource}/analyzer/?start=${startDate}&end=${endDate}`)
  },
  deleteBill(id){
    return billRepository.delete(`${resource}/${id}`)
  },
  // getUserById(userId) {
  //   return billRepository.get(`${resource}/${userId}`);
  // },
  // getFollowers(userId) {
  //   return billRepository.get(`${resource}/${userId}/follower`);
  // },
  // getFollowings(userId) {
  //   return billRepository.get(`${resource}/${userId}/following`);
  // },
  // follow(targetId) {
  //   return billRepository.post(`${resource}/follow/${targetId}`);
  // },
  // getMyPosts(userId, page) {
  //   return billRepository.get(`${resource}/${userId}/posts?page=${page}`)
  // },
  // changeuserinfo(dataRequest){
  //   return billRepository.post(`${resource}`, dataRequest);
  // },
  // searchUser(page, key){
  //   return billRepository.post(`${resource}/search?page=${page}&user=${key}`);
  // },
  // searchHashtag(page, key){
  //   return billRepository.post(`${resource}/search?page=${page}&hashtag=${key}`);
  // },
  // changeAvatar(dataRequest){
  //   return billRepository.post(`${resource}`, dataRequest);
  // }
};
