package com.base.newPeaceSystemBuild.repository

import com.base.newPeaceSystemBuild.vo.client.Client
import com.base.newPeaceSystemBuild.vo.client.Funeral
import com.base.newPeaceSystemBuild.vo.member.Bank
import com.base.newPeaceSystemBuild.vo.member.Department
import com.base.newPeaceSystemBuild.vo.member.Member
import com.base.newPeaceSystemBuild.vo.member.Role
import org.apache.ibatis.annotations.*

@Mapper
interface ClientRepository {
    @Select(
        """
            SELECT LAST_INSERT_ID();
        """
    )
    fun getLastInsertId(): Int

    @Insert(
        """
            INSERT INTO `client`
            SET regDate = NOW(),
            updateDate = NOW(),
            memberId = #{memberId},
            deceasedName = #{deceasedName},
            relatedName = #{relatedName},
            cellphoneNo = #{cellphoneNo},
            location = #{location},
            address = #{address}
        """
    )
    fun insertIntoClient(memberId: Int, deceasedName: String, relatedName: String, cellphoneNo: String, location: String, address: String)

    @Select(
        """
            SELECT *
            FROM `client`
            WHERE id = #{clientId}
        """
    )
    fun getClientById(clientId: Int): Client?
    @Select(
        """
            SELECT *
            FROM funeral
            WHERE clientId = #{clientId}
        """
    )
    fun getFuneralByClientId(clientId: Int): Funeral?

    @Select(
        """
            SELECT * 
            FROM `funeral` 
            WHERE progress = 1 
            AND directorMemberId = #{directorMemberId}
            
        """
    )
    fun getProgressingFuneralByDirectorMemberId(directorMemberId: Int): Funeral?

    @Update(
        """
            UPDATE `client`
            SET directorMemberId = #{directorMemberId}
            WHERE id = #{clientId};
        """
    )
    fun modifyClientIntoDirectorMemberIdByClientId(directorMemberId: Int, clientId: Int)

    @Insert(
        """
            INSERT INTO funeral
            SET regDate = NOW(),
            updateDate =NOW(),
            clientId = #{clientId},
            directorMemberId = #{directorMemberId},
            memberId = #{memberId},
            progress = 1
        """
    )
    fun insertFuneral(memberId: Int, directorMemberId: Int, clientId: Int)

    @Select(
        """
            SELECT *
            FROM funeral
            WHERE directorMemberId = #{directorMemberId}
            AND progress = 1
        """
    )
    fun getProgressingFuneralByIdDirectorMemberId(directorMemberId: Int): Funeral?

    @Select(
        """
            SELECT CONCAT(
            SUBSTR(cellphoneNo, 1, 3)
            , '-'
            , SUBSTR(cellphoneNo, 4, 4)
            , '-'
            , SUBSTR(cellphoneNo, 8, 4)
            ) AS `cellphoneNo`
            FROM `client`
            WHERE id = #{id};
        """
    )
    fun getCellphoneNoFormatted(id: Int): String
}
