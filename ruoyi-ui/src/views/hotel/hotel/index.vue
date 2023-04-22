<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="hotelName">
        <el-input
          v-model="queryParams.hotelName"
          placeholder="请输入名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="地址" prop="hotelAddress">
        <el-input
          v-model="queryParams.hotelAddress"
          placeholder="请输入地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="负责人" prop="hotelPerson">
        <el-input
          v-model="queryParams.hotelPerson"
          placeholder="请输入负责人"
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
          v-hasPermi="['hotel:hotel:add']"
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
          v-hasPermi="['hotel:hotel:edit']"
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
          v-hasPermi="['hotel:hotel:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['hotel:hotel:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="hotelList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="hotelId" />
      <el-table-column label="名称" align="center" prop="hotelName" />
      <el-table-column label="地址" align="center" prop="hotelAddress" />
      <el-table-column label="联系方式" align="center" prop="hotelTel" />
      <el-table-column label="负责人" align="center" prop="hotelPerson" />
      <el-table-column label="创建时间" align="center" prop="hotelCreateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.hotelCreateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="hotelUpdateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.hotelUpdateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="封面图" align="center" prop="hotelPic" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.hotelPic" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="省市区" align="center" prop="hotelSsq" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['hotel:hotel:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['hotel:hotel:remove']"
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

    <!-- 添加或修改酒店管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="hotelName">
          <el-input v-model="form.hotelName" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="地址" prop="hotelAddress">
          <el-input v-model="form.hotelAddress" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="联系方式" prop="hotelTel">
          <el-input v-model="form.hotelTel" placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="负责人" prop="hotelPerson">
          <el-select v-model="form.hotelPerson" placeholder="请选择酒店负责人">
            <el-option
              v-for="item in UserList"
              :key="item.value"
              :label="item.label"
              :value="item.nickName">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="封面图" prop="hotelPic">
          <image-upload v-model="form.hotelPic"/>
        </el-form-item>
        <el-form-item label="省市区" prop="hotelSsq">
          <el-select v-model="province" clearable placeholder="省" @change="seletekey($event, 'province')">
          <el-option v-for="(val, key) in provinces" :key="key" :label="val" :value="val" />
        </el-select>
          <el-select v-model="city" clearable placeholder="市" @change="seletekey($event, 'city')">
            <el-option v-for="(val, key) in citys" :key="key" :label="val" :value="val" />
          </el-select>
          <el-select v-model="area" clearable placeholder="区" @change="seletekey($event, 'area')">
            <el-option v-for="(val,key) in areas" :key="key" :label="val" :value="val" />
          </el-select>

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
import { listHotel, getHotel, delHotel, addHotel, updateHotel } from "@/api/hotel/hotel";
import { listUser } from "@/api/system/user";
import { pcaa } from 'area-data';


export default {
  name: "Hotel",
  data() {
    return {
      UserList:[],

      province:'',
      city:'',
      area:'',
      pcaaData: pcaa,
      // 区域数据
      provinces: pcaa['86'],
      citys: [],
      areas: [],
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
      // 酒店管理表格数据
      hotelList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        hotelName: null,
        hotelAddress: null,
        hotelPerson: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        hotelName: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        hotelAddress: [
          { required: true, message: "地址不能为空", trigger: "blur" }
        ],
        hotelTel: [
          { required: true, message: "联系方式不能为空", trigger: "blur" }
        ],
        hotelPerson: [
          { required: true, message: "负责人不能为空", trigger: "blur" }
        ],
        hotelPic: [
          { required: true, message: "封面图不能为空", trigger: "blur" }
        ],
        hotelSsq: [
          { required: true, message: "省市区不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getUserList();

  },
  methods: {

    seletekey(e, type) {
      if (e) {
        switch (type) {
          case 'province':
            Object.keys(this.provinces).filter(item => {
              if (this.provinces[item] === e) {
                this.citys = this.pcaaData[item]
              }
            })
            break
          case 'city':
            Object.keys(this.citys).filter(item => {
              if (this.citys[item] === e) {
                this.areas = this.pcaaData[item]
              }
            })
            break
          case 'area':
            break
        }
      }
    },
    /** 查询酒店管理列表 */
    getList() {
      this.loading = true;
      listHotel(this.queryParams).then(response => {
        this.hotelList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getUserList() {
      this.loading = true;
      listUser(this.queryParams).then(response => {
        this.UserList = response.rows;
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
        hotelId: null,
        hotelName: null,
        hotelAddress: null,
        hotelTel: null,
        hotelPerson: null,
        hotelCreateTime: null,
        hotelUpdateTime: null,
        hotelPic: null,
        hotelSsq: null
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
      this.ids = selection.map(item => item.hotelId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加酒店管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const hotelId = row.hotelId || this.ids
      getHotel(hotelId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改酒店管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.form.hotelSsq = this.province+this.city+this.area;
      console.log(this.form.hotelSsq);
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.hotelId != null) {
            updateHotel(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addHotel(this.form).then(response => {
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
      const hotelIds = row.hotelId || this.ids;
      this.$modal.confirm('是否确认删除酒店管理编号为"' + hotelIds + '"的数据项？').then(function() {
        return delHotel(hotelIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('hotel/hotel/export', {
        ...this.queryParams
      }, `hotel_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
