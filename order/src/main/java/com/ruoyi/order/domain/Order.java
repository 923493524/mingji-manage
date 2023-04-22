package com.ruoyi.order.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单管理对象 h_order
 * 
 * @author ruoyi
 * @date 2023-04-22
 */
public class Order extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    private String orderId;

    /** 关联用户 */
    @Excel(name = "关联用户")
    private Long userId;

    /** 关联酒店 */
    @Excel(name = "关联酒店")
    private Long hotelId;

    /** 关联酒店名称 */
    @Excel(name = "关联酒店名称")
    private String hotelName;

    /** 关联房间 */
    @Excel(name = "关联房间")
    private Long roomId;

    /** 关联房间名称 */
    @Excel(name = "关联房间名称")
    private String roomName;

    /** 房间数量 */
    @Excel(name = "房间数量")
    private Integer roomNumber;

    /** 订单金额 */
    @Excel(name = "订单金额")
    private Integer orderPrice;

    /** 已付金额 */
    @Excel(name = "已付金额")
    private Integer orderPaid;

    /** 状态 */
    @Excel(name = "状态")
    private String orderType;

    /** 备注 */
    @Excel(name = "备注")
    private String orderRemarks;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderCreateTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderUpdateTime;

    /** 订单开始时间 */
    @Excel(name = "订单开始时间")
    private String startTime;

    /** 订单结束时间 */
    @Excel(name = "订单结束时间")
    private String endTime;

    /** 入住人姓名（可能多个） */
    @Excel(name = "入住人姓名", readConverterExp = "可=能多个")
    private String occupantList;

    /** 联系人电话 */
    @Excel(name = "联系人电话")
    private String occupantTel;

    /** 预计入住时间 */
    @Excel(name = "预计入住时间")
    private String occupantTime;

    public void setOrderId(String orderId) 
    {
        this.orderId = orderId;
    }

    public String getOrderId() 
    {
        return orderId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setHotelId(Long hotelId) 
    {
        this.hotelId = hotelId;
    }

    public Long getHotelId() 
    {
        return hotelId;
    }
    public void setHotelName(String hotelName) 
    {
        this.hotelName = hotelName;
    }

    public String getHotelName() 
    {
        return hotelName;
    }
    public void setRoomId(Long roomId) 
    {
        this.roomId = roomId;
    }

    public Long getRoomId() 
    {
        return roomId;
    }
    public void setRoomName(String roomName) 
    {
        this.roomName = roomName;
    }

    public String getRoomName() 
    {
        return roomName;
    }
    public void setRoomNumber(Integer roomNumber) 
    {
        this.roomNumber = roomNumber;
    }

    public Integer getRoomNumber() 
    {
        return roomNumber;
    }
    public void setOrderPrice(Integer orderPrice) 
    {
        this.orderPrice = orderPrice;
    }

    public Integer getOrderPrice() 
    {
        return orderPrice;
    }
    public void setOrderPaid(Integer orderPaid) 
    {
        this.orderPaid = orderPaid;
    }

    public Integer getOrderPaid() 
    {
        return orderPaid;
    }
    public void setOrderType(String orderType) 
    {
        this.orderType = orderType;
    }

    public String getOrderType() 
    {
        return orderType;
    }
    public void setOrderRemarks(String orderRemarks) 
    {
        this.orderRemarks = orderRemarks;
    }

    public String getOrderRemarks() 
    {
        return orderRemarks;
    }
    public void setOrderCreateTime(Date orderCreateTime) 
    {
        this.orderCreateTime = orderCreateTime;
    }

    public Date getOrderCreateTime() 
    {
        return orderCreateTime;
    }
    public void setOrderUpdateTime(Date orderUpdateTime) 
    {
        this.orderUpdateTime = orderUpdateTime;
    }

    public Date getOrderUpdateTime() 
    {
        return orderUpdateTime;
    }
    public void setStartTime(String startTime) 
    {
        this.startTime = startTime;
    }

    public String getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(String endTime) 
    {
        this.endTime = endTime;
    }

    public String getEndTime() 
    {
        return endTime;
    }
    public void setOccupantList(String occupantList) 
    {
        this.occupantList = occupantList;
    }

    public String getOccupantList() 
    {
        return occupantList;
    }
    public void setOccupantTel(String occupantTel) 
    {
        this.occupantTel = occupantTel;
    }

    public String getOccupantTel() 
    {
        return occupantTel;
    }
    public void setOccupantTime(String occupantTime) 
    {
        this.occupantTime = occupantTime;
    }

    public String getOccupantTime() 
    {
        return occupantTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("userId", getUserId())
            .append("hotelId", getHotelId())
            .append("hotelName", getHotelName())
            .append("roomId", getRoomId())
            .append("roomName", getRoomName())
            .append("roomNumber", getRoomNumber())
            .append("orderPrice", getOrderPrice())
            .append("orderPaid", getOrderPaid())
            .append("orderType", getOrderType())
            .append("orderRemarks", getOrderRemarks())
            .append("orderCreateTime", getOrderCreateTime())
            .append("orderUpdateTime", getOrderUpdateTime())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("occupantList", getOccupantList())
            .append("occupantTel", getOccupantTel())
            .append("occupantTime", getOccupantTime())
            .toString();
    }
}
