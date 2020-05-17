import BillRepository from "./billRepository";
import AuthRepository from "./authRepository";
import UserRepository from "./userRepository";
import SettingRepository from "./settingRepository";
import GroupRepository from "./groupRepository";

const repositories = {
  bill: BillRepository,
  auth: AuthRepository,
  user: UserRepository,
  setting: SettingRepository,
  group: GroupRepository
};

export const RepositoryFactory = {
  get: name => repositories[name]
};
