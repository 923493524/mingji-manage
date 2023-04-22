<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="类型" prop="roomName">
        <el-input
          v-model="queryParams.roomName"
          placeholder="请输入类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="价格" prop="roomPrice">
        <el-input
          v-model="queryParams.roomPrice"
          placeholder="请输入价格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数量" prop="roomNumber">
        <el-input
          v-model="queryParams.roomNumber"
          placeholder="请输入数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="余量" prop="roomRemainNumber">
        <el-input
          v-model="queryParams.roomRemainNumber"
          placeholder="请输入余量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="归属酒店名称" prop="roomInHotelName">
        <el-select v-model="queryParams.roomInHotelName" filterable placeholder="请选择归属酒店名称" clearable>
          <el-option
            v-for="item in hotelList"
            :key="item.value"
            :label="item.label"
            :value="item.hotelName">
          </el-option>
        </el-select>
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
          v-hasPermi="['room:room:add']"
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
          v-hasPermi="['room:room:edit']"
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
          v-hasPermi="['room:room:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['room:room:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="roomList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="roomId" />
      <el-table-column label="类型" align="center" prop="roomName" />
      <el-table-column label="价格" align="center" prop="roomPrice" />
      <el-table-column label="数量" align="center" prop="roomNumber" />
      <el-table-column label="余量" align="center" prop="roomRemainNumber" />
      <el-table-column label="归属酒店名称" align="center" prop="roomInHotelName">
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="roomCreateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.roomCreateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="roomUpdateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.roomUpdateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="简介" align="center" prop="roomProfile" />
      <el-table-column label="封面" align="center" prop="roomPic" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.roomPic" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['room:room:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['room:room:remove']"
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

    <!-- 添加或修改房间管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="类型" prop="roomName">
          <el-input v-model="form.roomName" placeholder="请输入类型" />
        </el-form-item>
        <el-form-item label="价格" prop="roomPrice">
          <el-input v-model="form.roomPrice" placeholder="请输入价格" />
        </el-form-item>
        <el-form-item label="数量" prop="roomNumber" @change="setnum">
          <el-input v-model="form.roomNumber" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item label="余量" prop="roomRemainNumber">
          <el-input v-model="form.roomRemainNumber" disabled></el-input>
        </el-form-item>
        <el-form-item label="归属酒店" prop="roomInHotelName">
          <el-select v-model="form.roomInHotelName" placeholder="请选择归属酒店名称" @change="sethotelid">
            <el-option
              v-for="item in hotelList"
              :key="item.value"
              :label="item.label"
              :value="item.hotelName">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="酒店id" prop="roomInHotelId">
          <el-input placeholder="请选择归属酒店名称" v-model="form.roomInHotelId" disabled></el-input>
        </el-form-item>
        <el-form-item label="简介">
          <editor v-model="form.roomProfile" :min-height="192"/>
        </el-form-item>
        <el-form-item label="封面" prop="roomPic">
          <image-upload v-model="form.roomPic"/>
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
import { listRoom, getRoom, delRoom, addRoom, updateRoom, } from "@/api/room/room";
import { listHotel} from "@/api/hotel/hotel";


export default {
  name: "Room",
  dicts: ['sys_user_sex','h_hotel'],
  data() {
    return {
      hotelList: [],
      hotelId:[],
      hoteltotal: 0,
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
      // 房间管理表格数据
      roomList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        roomName: null,
        roomPrice: null,
        roomNumber: null,
        roomRemainNumber: null,
        roomInHotelName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        roomName: [
          { required: true, message: "类型不能为空", trigger: "blur" }
        ],
        roomPrice: [
          { required: true, message: "价格不能为空", trigger: "blur" }
        ],
        roomNumber: [
          { required: true, message: "数量不能为空", trigger: "blur" }
        ],
        roomInHotelName: [
          { required: true, message: "归属酒店名称不能为空", trigger: "change" }
        ],
        roomProfile: [
          { required: true, message: "简介不能为空", trigger: "blur" }
        ],
        roomPic: [
          { required: true, message: "封面不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getHotelList();

  },
  methods: {
    /** 查询房间管理列表 */
    getList() {
      this.loading = true;
      listRoom(this.queryParams).then(response => {
        this.roomList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getHotelList() {
      this.loading = true;
      listHotel(this.queryParams).then(response => {
        // var len = response.rows.length ;
        // for (let i = 0; i < len; i++) {
        //   this.hotelName[i] = response.rows[i].hotelName;
        //   this.hotelId[i] = response.rows[i].hotelId;
        // }
        this.hotelList = response.rows;
        this.loading = false;
      });

    },
    sethotelid(){
      const item = this.hotelList.find(item1 => item1.hotelName === this.form.roomInHotelName);
      this.form.roomInHotelId = item.hotelId;
    },
    setnum(){
      this.form.roomRemainNumber = this.form.roomNumber;
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        roomId: null,
        roomName: null,
        roomPrice: null,
        roomNumber: null,
        roomRemainNumber: null,
        roomInHotelId: null,
        roomInHotelName: null,
        roomCreateTime: null,
        roomUpdateTime: null,
        roomProfile: null,
        roomPic: null
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
      this.ids = selection.map(item => item.roomId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加房间管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const roomId = row.roomId || this.ids
      getRoom(roomId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改房间管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.form.roomRemainNumber = this.form.roomNumber;
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.roomId != null) {
            updateRoom(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRoom(this.form).then(response => {
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
      const roomIds = row.roomId || this.ids;
      this.$modal.confirm('是否确认删除房间管理编号为"' + roomIds + '"的数据项？').then(function() {
        return delRoom(roomIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('room/room/export', {
        ...this.queryParams
      }, `room_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
