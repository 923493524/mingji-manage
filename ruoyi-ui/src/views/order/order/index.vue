<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="关联酒店名称" prop="hotelName">
        <el-input
          v-model="queryParams.hotelName"
          placeholder="请输入关联酒店名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="入住人姓名" prop="occupantList">
        <el-input
          v-model="queryParams.occupantList"
          placeholder="请输入入住人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系人电话" prop="occupantTel">
        <el-input
          v-model="queryParams.occupantTel"
          placeholder="请输入联系人电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['order:order:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['order:order:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['order:order:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['order:order:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="orderId" />
      <el-table-column label="关联用户" align="center" prop="userId" />
      <el-table-column label="关联酒店" align="center" prop="hotelName" />
      <el-table-column label="关联房间" align="center" prop="roomName" />
      <el-table-column label="房间数量" align="center" prop="roomNumber" />
      <el-table-column label="订单金额" align="center" prop="orderPrice" />
      <el-table-column label="状态" align="center" prop="orderType" />
      <el-table-column label="备注" align="center" prop="orderRemarks" />
      <el-table-column label="创建时间" align="center" prop="orderCreateTime" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.orderCreateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="orderUpdateTime" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.orderUpdateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单开始时间" align="center" prop="startTime"  width="100"/>
      <el-table-column label="订单结束时间" align="center" prop="endTime"  width="100"/>
      <el-table-column label="入住人姓名" align="center" prop="occupantList" width="100"/>
      <el-table-column label="联系人电话" align="center" prop="occupantTel" width="100"/>
      <el-table-column label="预计入住时间" align="center" prop="occupantTime" width="100"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['order:order:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['order:order:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改订单管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="订单状态" prop="orderType">
          <el-select v-model="form.orderType" placeholder="请选择订单状态">
            <el-option
              v-for="item in TypeList"
              :key="item.value"
              :label="item.label"
              :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="关联用户" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入关联用户" />
        </el-form-item>
        <el-form-item label="关联酒店" prop="hotelId">
          <el-input v-model="form.hotelId" placeholder="请输入关联酒店" disabled/>
        </el-form-item>
        <el-form-item label="关联酒店名称" prop="hotelName">
          <el-select v-model="form.hotelName" placeholder="请选择酒店" @change="sethotelid">
            <el-option
              v-for="item in hotelList"
              :key="item.value"
              :label="item.label"
              :value="item.hotelName">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="关联房间" prop="roomId">
          <el-input v-model="form.roomId" placeholder="请输入关联房间" disabled/>
        </el-form-item>
        <el-form-item label="关联房间名称" prop="roomName">
          <el-select v-model="form.roomName" placeholder="请选择房间" @change="setroomid">
            <el-option
              v-for="item in roomList2"
              :key="item.value"
              :label="item.label"
              :value="item.roomName">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="房间数量" prop="roomNumber">
          <el-input v-model="form.roomNumber" placeholder="请输入房间数量" />
        </el-form-item>
        <el-form-item label="订单金额" prop="orderPrice">
          <el-input v-model="form.orderPrice" placeholder="请输入订单金额" />
        </el-form-item>
        <el-form-item label="已付金额" prop="orderPaid">
          <el-input v-model="form.orderPaid" placeholder="请输入已付金额" />
        </el-form-item>
        <el-form-item label="备注" prop="orderRemarks">
          <el-input v-model="form.orderRemarks" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="订单开始时间" prop="startTime">
          <el-input v-model="form.startTime" placeholder="请输入订单开始时间" />
        </el-form-item>
        <el-form-item label="订单结束时间" prop="endTime">
          <el-input v-model="form.endTime" placeholder="请输入订单结束时间" />
        </el-form-item>
        <el-form-item label="入住人姓名" prop="occupantList">
          <el-input v-model="form.occupantList" placeholder="请输入入住人姓名" />
        </el-form-item>
        <el-form-item label="联系人电话" prop="occupantTel">
          <el-input v-model="form.occupantTel" placeholder="请输入联系人电话" />
        </el-form-item>
        <el-form-item label="预计入住时间" prop="occupantTime">
          <el-input v-model="form.occupantTime" placeholder="请输入预计入住时间" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listOrder, getOrder, delOrder, addOrder, updateOrder } from "@/api/order/order";
import {listHotel} from "@/api/hotel/hotel";
import {listRoom} from "@/api/room/room";

export default {
  name: "Order",
  data() {
    return {
      TypeList:[
        "待支付",
        "待入住",
        "进行中",
        "已取消",
        "已退款",
      ],
      hotelList: [],
      roomList:[],
      roomList2:[],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 订单管理表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        hotelName: null,
        orderType: null,
        occupantList: null,
        occupantTel: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        occupantList: [
          { required: true, message: "入住人姓名不能为空", trigger: "blur" }
        ],
        occupantTel: [
          { required: true, message: "联系人电话不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getHotelList();
    this.getRoomList();
  },
  methods: {
    getHotelList() {
      this.loading = true;
      listHotel(this.queryParams).then(response => {
        this.hotelList = response.rows;
        this.loading = false;
      });

    },
    sethotelid(){
      this.roomList2 = [];
      const item = this.hotelList.find(item1 => item1.hotelName === this.form.hotelName);
      this.form.hotelId = item.hotelId;
      this.setroomlist();
    },
    setroomlist(){
      let list = this.roomList;
      let list2 = this.roomList2;
      let j = 0;
      for (let i = 0; i < list.length; i++) {
        if (list[i].roomInHotelId === this.form.hotelId){
          list2[j] = list[i];
          j++;
        }
      }
      this.roomList2 = list2;
    },

    getRoomList() {
      this.loading = true;
      listRoom(this.queryParams).then(response => {
        this.roomList = response.rows;
        this.loading = false;
      });

    },
    setroomid(){
      const item = this.roomList2.find(item1 => item1.roomName === this.form.roomName);
      this.form.roomId = item.roomId;
    },


    /** 查询订单管理列表 */
    getList() {
      this.loading = true;
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        orderId: null,
        userId: null,
        hotelId: null,
        hotelName: null,
        roomId: null,
        roomName: null,
        roomNumber: null,
        orderPrice: null,
        orderPaid: null,
        orderType: null,
        orderRemarks: null,
        orderCreateTime: null,
        orderUpdateTime: null,
        startTime: null,
        endTime: null,
        occupantList: null,
        occupantTel: null,
        occupantTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.orderId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加订单管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const orderId = row.orderId || this.ids
      getOrder(orderId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改订单管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.orderId != null) {
            updateOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrder(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const orderIds = row.orderId || this.ids;
      this.$modal.confirm('是否确认删除订单管理编号为"' + orderIds + '"的数据项？').then(function() {
        return delOrder(orderIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('order/order/export', {
        ...this.queryParams
      }, `order_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
