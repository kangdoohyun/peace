package com.base.newPeaceSystemBuild.repository

import com.base.newPeaceSystemBuild.vo.member.Bank
import com.base.newPeaceSystemBuild.vo.member.Department
import com.base.newPeaceSystemBuild.vo.member.Member
import com.base.newPeaceSystemBuild.vo.member.Role
import org.apache.ibatis.annotations.*

@Mapper
interface MemberRepository {
    @Select(
        """
            SELECT 
            M.*,
            MR.regDate AS `extra__regDate`,
            MR.updateDate AS `extra__updateDate`,
            MR.introduce AS `extra__introduce`,
            MR.authenticationStatus AS `extra__authenticationStatus`,
            MR.authenticationDate AS `extra__authenticationDate`,
            R.roleName AS `extra__roleName`
            FROM `member` AS M
            LEFT JOIN memberRole AS MR
            ON M.id = MR.memberId
            LEFT JOIN `role` AS R
            ON M.roleLevel = R.id
            WHERE M.loginId = #{loginId}
        """
    )
    fun getMemberByLoginId(@Param("loginId") loginId: String): Member?

    @Select(
        """
            SELECT 
            M.*,
            MR.regDate AS `extra__regDate`,
            MR.updateDate AS `extra__updateDate`,
            MR.introduce AS `extra__introduce`,
            MR.authenticationStatus AS `extra__authenticationStatus`,
            MR.authenticationDate AS `extra__authenticationDate`,
            R.roleName AS `extra__roleName`
            FROM `member` AS M
            LEFT JOIN memberRole AS MR
            ON M.id = MR.memberId
            LEFT JOIN `role` AS R
            ON M.roleLevel = R.id
            WHERE M.email = #{email}
        """
    )
    fun getMemberByEmail(email: String): Member?

    @Select(
        """
            SELECT 
            M.*,
            MR.regDate AS `extra__regDate`,
            MR.updateDate AS `extra__updateDate`,
            MR.introduce AS `extra__introduce`,
            MR.authenticationStatus AS `extra__authenticationStatus`,
            MR.authenticationDate AS `extra__authenticationDate`,
            R.roleName AS `extra__roleName`
            FROM `member` AS M
            LEFT JOIN memberRole AS MR
            ON M.id = MR.memberId
            LEFT JOIN `role` AS R
            ON M.roleLevel = R.id
            WHERE M.id = #{id}
        """
    )
    fun getMemberById(id: Int): Member?

    @Select(
        """
            SELECT 
            M.*,
            MR.regDate AS `extra__regDate`,
            MR.updateDate AS `extra__updateDate`,
            MR.introduce AS `extra__introduce`,
            MR.authenticationStatus AS `extra__authenticationStatus`,
            MR.authenticationDate AS `extra__authenticationDate`,
            R.roleName AS `extra__roleName`
            FROM `member` AS M
            LEFT JOIN memberRole AS MR
            ON M.id = MR.memberId
            LEFT JOIN `role` AS R
            ON M.roleLevel = R.id
            WHERE MR.authenticationStatus = #{authenticationStatus}
        """
    )
    fun getMembersByAuthenticationStatus(authenticationStatus: Int): List<Member>?

    @Insert(
        """
        INSERT INTO `member`
        SET regDate = NOW(),
        updateDate = NOW(),
        loginId = #{loginId},
        loginPw = #{loginPw},
        `name` = #{name},
        cellphoneNo = #{cellphoneNo},
        email = #{email},
        location = #{location},
        bank = #{bank},
        accountNum = #{accountNum}
        """
    )
    fun join(
        loginId: String,
        loginPw: String,
        name: String,
        cellphoneNo: String,
        email: String,
        location: String,
        bank: String,
        accountNum: String
    )
    @Select(
        """
        SELECT R.*
        FROM `role` AS R
        WHERE id != 1
        """
    )
    fun getRoles(): List<Role>

    @Select(
        """
        SELECT D.*
        FROM `department` AS D
        """
    )
    fun getDepartments(): List<Department>

    @Select(
        """
        SELECT B.*
        FROM `bank` AS B
        """
    )
    fun getBanks(): List<Bank>

    @Update(
        """
            UPDATE memberRole SET 
            authenticationStatus = #{authenticationStatus}, 
            authenticationDate = NOW() 
            WHERE memberId = #{memberId}
        """
    )
    fun updateAuthenticationStatus(memberId: Int, authenticationStatus: Int)

    @Update(
        """
            UPDATE `member` SET 
            roleLevel = #{authenticationStatus}
            WHERE id = #{memberId};
        """
    )
    fun updateRoleLevel(memberId: Int, authenticationStatus: Int)

    @Select(
        """
            SELECT CONCAT(
            SUBSTR(cellphoneNo, 1, 3)
            , '-'
            , SUBSTR(cellphoneNo, 4, 4)
            , '-'
            , SUBSTR(cellphoneNo, 8, 4)
            ) AS `cellphoneNo`
            FROM `member`
            WHERE id = #{id};
        """
    )
    fun getCellphoneNoFormatted(id: Int): String

}
