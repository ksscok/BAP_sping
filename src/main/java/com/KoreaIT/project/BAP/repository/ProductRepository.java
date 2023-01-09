package com.KoreaIT.project.BAP.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.KoreaIT.project.BAP.vo.Product;

@Mapper
public interface ProductRepository {

	@Select("""
			<script>
			SELECT p.*,
			MIN(fee) AS extra__minFee
			FROM product AS p
			WHERE 1
			<if test="searchKeyword != ''">
				AND (
					name LIKE CONCAT('%', #{searchKeyword}, '%')
					OR
					address LIKE CONCAT('%', #{searchKeyword}, '%')
				)
			</if>
			<if test="low_price != 1">
				AND fee <![CDATA[>=]]> #{low_price}
			</if>
			<if test="high_price != 999999999">
				AND fee <![CDATA[<=]]> #{high_price}
			</if>
			<if test="motelType != '' || hotelType != '' || pensionType != '' || geusthouseType != ''">
				AND
				<if test="motelType != ''">
					accommodationType = '모텔'
				</if>
				<if test="hotelType != ''">
					<if test="motelType != ''">
					OR
					</if>
					accommodationType = '호텔'
				</if>
				<if test="pensionType != ''">
					<if test="motelType != '' || hotelType != ''">
					OR
					</if>
					accommodationType = '펜션'
				</if>
				<if test="geusthouseType != ''">
					<if test="motelType != '' || hotelType != '' || pensionType != ''">
					OR
					</if>
					accommodationType = '게스트하우스'
				</if>
			</if>
			GROUP BY name, address
			<if test="order_by != ''">
				<choose>
					<when test="order_by == 'lowPrice'">
						ORDER BY extra__minFee ASC
					</when>
					<when test="order_by == 'highPrice'">
						ORDER BY extra__minFee DESC
					</when>
				</choose>
			</if>
			</script>
			""")
	List<Product> getForPrintproducts(String searchKeyword, String order_by, String motelType, String hotelType, String pensionType, String geusthouseType, int low_price, int high_price);

	@Select("""
			SELECT p.*,
					c.name AS comName, 
					c.address AS comAddr
				FROM product AS p
				INNER JOIN company AS c
				ON p.companyId = c.id
				WHERE p.companyId = #{companyId}
				GROUP BY p.companyId
			""")
	Product getForPrintproduct(int companyId);

	@Select("""
			SELECT *
				FROM product
				WHERE companyId = #{companyId}
			""")
	List<Product> getproductsAtCompanyId(int companyId);
}
