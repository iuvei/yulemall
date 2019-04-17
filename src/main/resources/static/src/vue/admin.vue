<template>
    <v-card>
        <v-card-title>
            <v-btn color="primary" @click="addAdmin">新增城市</v-btn>
            <v-spacer/>
            <v-select
                    :items="parameters"
                    box
                    label="搜索类型"
                    item-text="name"
                    item-value="id"
                    v-model='itemSelect'
            ></v-select>
            <!--搜索框，与search属性关联-->
            <v-spacer/>
            <v-flex xs3>
                <v-text-field label="输入关键字搜索" v-model.lazy="search" append-icon="search" hide-details/>
            </v-flex>
        </v-card-title>
        <v-divider/>
        <v-data-table
                :headers="headers"
                :items="Admin"
                :pagination.sync="pagination"
                :total-items="totalBrands"
                :loading="loading"
                class="elevation-1"
        >
            <template slot="items" slot-scope="props">
                <td class="text-xs-center">{{ props.item.id }}</td>
                <td class="justify-center layout px-0">
                    <v-btn icon @click="editAdmin(props.item)">
                        <i class="el-icon-edit"/>
                    </v-btn>
                    <v-btn icon @click="deleteAdmin(props.item)">
                        <i class="el-icon-delete"/>
                    </v-btn>
                </td>
            </template>
        </v-data-table>
        <!--弹出的对话框-->
        <v-dialog max-width="500" v-model="show" persistent scrollable>
            <v-card>
                <!--对话框的标题-->
                <v-toolbar dense dark color="primary">
                    <v-toolbar-title>{{isEdit ? '修改' : '新增'}}城市</v-toolbar-title>
                    <v-spacer/>
                    <!--关闭窗口的按钮-->
                    <v-btn icon @click="closeWindow">
                        <v-icon>close</v-icon>
                    </v-btn>
                </v-toolbar>
                <!--对话框的内容，表单-->
                <v-card-text class="px-5" style="height:400px">
                    <Admin-form @close="closeWindow" :oldAdmin="oldAdmin" :isEdit="isEdit"/>
                </v-card-text>
            </v-card>
        </v-dialog>
    </v-card>
</template>

<script>
    // 导入自定义的表单组件
    import AdminForm from './AdminForm'

    export default {
        name: "Admin",
        data() {
            return {
                parameters: [
                    {name: "主键", parameter: "id"},
                    {name: "消息内容", parameter: "message"}
                ],
                itemSelect: '',
                search: '', // 搜索过滤字段
                totalBrands: 0, // 总条数
                Admin: [], // 当前页品牌数据
                loading: true, // 是否在加载中
                pagination: {}, // 分页信息
                headers: [
                    {text: 'id', align: 'center', value: 'id'},
                    {text: '操作', align: 'center', value: 'id', sortable: false}
                ],
                show: false,// 控制对话框的显示
                oldAdmin: {}, // 即将被编辑的品牌数据
                isEdit: false, // 是否是编辑
            }
        },
        mounted() { // 渲染后执行
            // 查询数据
            this.getDataFromServer();
        },
        watch: {
            pagination: { // 监视pagination属性的变化
                deep: true, // deep为true，会监视pagination的属性及属性中的对象属性变化
                handler() {
                    // 变化后的回调函数，这里我们再次调用getDataFromServer即可
                    this.getDataFromServer();
                }
            },
            search: { // 监视搜索字段
                handler() {
                    this.getDataFromServer();
                }
            }
        },
        methods: {
            getDataFromServer() { // 从服务的加载数的方法。
                // 发起请求
                this.$http.get("admin", {
                    params: {
                        key: this.search, // 搜索条件
                        page: this.pagination.page,// 当前页
                        rows: this.pagination.rowsPerPage,// 每页大小
                        sortBy: this.pagination.sortBy,// 排序字段
                        desc: this.pagination.descending// 是否降序
                    }
                }).then(resp => { // 这里使用箭头函数
                    this.Admin = resp.data.data;
                this.totalBrands = resp.data.total;
                // 完成赋值后，把加载状态赋值为false
                this.loading = false;
            })
            },
            addAdmin() {
                // 修改标记
                this.isEdit = false;
                // 控制弹窗可见：
                this.show = true;
                // 把oldBrand变为null
                this.oldAdmin = null;
            },
            editAdmin(admin) {
                console.log(admin.id)
                // 根据品牌信息查询商品分类
                this.$http.get("/admin/" + admin.id)
                    .then(({data}) => {
                    // 修改标记
                    this.isEdit = true;
                // 控制弹窗可见：
                this.show = true;
                // 获取要编辑的brand
                this.oldAdmin = admin
                // 回显商品分类
                this.oldAdmin.categories = data;
            })
            }, deleteAdmin(admin) {
                console.log(admin.id)
                this.$http.delete("admin/" + admin.id)
                    .then(({data}) => {
                    this.getDataFromServer();
            }
            )
            },
            closeWindow() {
                // 重新加载数据
                this.getDataFromServer();
                // 关闭窗口
                this.show = false;
            }
        },
        components: {
            AdminForm
        }
    }
</script>

<style scoped>

</style>
