package com.ruoyi.room.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 房间管理对象 h_room
 * 
 * @author luomoqi
 * @date 2023-04-20
 */
public class Room extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    private Long roomId;

    /** 类型 */
    @Excel(name = "类型")
    private String roomName;

    /** 价格 */
    @Excel(name = "价格")
    private Integer roomPrice;

    /** 数量 */
    @Excel(name = "数量")
    private Integer roomNumber;

    /** 余量 */
    @Excel(name = "余量")
    private Integer roomRemainNumber;

    /** 归属酒店id */
    private Long roomInHotelId;

    /** 归属酒店名称 */
    @Excel(name = "归属酒店名称")
    private String roomInHotelName;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date roomCreateTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date roomUpdateTime;

    /** 简介 */
    @Excel(name = "简介")
    private String roomProfile;

    /** 封面 */
    @Excel(name = "封面")
    private String roomPic;

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
    public void setRoomPrice(Integer roomPrice) 
    {
        this.roomPrice = roomPrice;
    }

    public Integer getRoomPrice() 
    {
        return roomPrice;
    }
    public void setRoomNumber(Integer roomNumber) 
    {
        this.roomNumber = roomNumber;
    }

    public Integer getRoomNumber() 
    {
        return roomNumber;
    }
    public void setRoomRemainNumber(Integer roomRemainNumber) 
    {
        this.roomRemainNumber = roomRemainNumber;
    }

    public Integer getRoomRemainNumber() 
    {
        return roomRemainNumber;
    }
    public void setRoomInHotelId(Long roomInHotelId) 
    {
        this.roomInHotelId = roomInHotelId;
    }

    public Long getRoomInHotelId() 
    {
        return roomInHotelId;
    }
    public void setRoomInHotelName(String roomInHotelName) 
    {
        this.roomInHotelName = roomInHotelName;
    }

    public String getRoomInHotelName() 
    {
        return roomInHotelName;
    }
    public void setRoomCreateTime(Date roomCreateTime) 
    {
        this.roomCreateTime = roomCreateTime;
    }

    public Date getRoomCreateTime() 
    {
        return roomCreateTime;
    }
    public void setRoomUpdateTime(Date roomUpdateTime) 
    {
        this.roomUpdateTime = roomUpdateTime;
    }

    public Date getRoomUpdateTime() 
    {
        return roomUpdateTime;
    }
    public void setRoomProfile(String roomProfile) 
    {
        this.roomProfile = roomProfile;
    }

    public String getRoomProfile() 
    {
        return roomProfile;
    }
    public void setRoomPic(String roomPic) 
    {
        this.roomPic = roomPic;
    }

    public String getRoomPic() 
    {
        return roomPic;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roomId", getRoomId())
            .append("roomName", getRoomName())
            .append("roomPrice", getRoomPrice())
            .append("roomNumber", getRoomNumber())
            .append("roomRemainNumber", getRoomRemainNumber())
            .append("roomInHotelId", getRoomInHotelId())
            .append("roomInHotelName", getRoomInHotelName())
            .append("roomCreateTime", getRoomCreateTime())
            .append("roomUpdateTime", getRoomUpdateTime())
            .append("roomProfile", getRoomProfile())
            .append("roomPic", getRoomPic())
            .toString();
    }
}
