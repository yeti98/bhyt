<template>
    <div>
        <Menu :theme="theme1" active-name="bill" mode="horizontal" v-on:on-select="updateActiveComponent">
            <MenuItem name="bill">
                <Icon type="ios-people"/>
                Xem danh sách
            </MenuItem>
            <MenuItem @click="activeComp=setting" name="statis">
                <Icon type="ios-stats"/>
                Thống kê
            </MenuItem>
            <MenuItem name="setting">
                <Icon type="ios-construct"/>
                Cấu hình
            </MenuItem>
            
            <div style="float: right">
                <Icon type="ios-contacts" />
                {{email}},
                <MenuItem name="username"  style="float: right">
                    <a @click="logout">Logout</a>
                </MenuItem>
            </div>
        </Menu>
        <div style="padding: 1%">
            <keep-alive>
                <component :is="activeComp"></component>
            </keep-alive>
        </div>
    </div>
</template>
<script>
  import Bill from "../components/Bill";
  import Setting from "../components/Setting";
  import Statistical from "../components/Statistical";

  export default {
    data() {
      return {
        theme1: 'light',
        activeComp: Bill,
        email: localStorage.getItem('email')
      }
    },
    created() {
      this.$on("on-select", name => {
        console.log(name)
      })
    },
    methods: {
        logout: function() {
            this.$store.dispatch("logout").then(() => {
                location.reload()
            });
        },
      updateActiveComponent(name) {
        if (name === 'bill') {
          this.activeComp = Bill
        } else if (name === 'statis') {
          this.activeComp = Statistical
        } else {
          this.activeComp = Setting
        }
      }
    }
  }
</script>
