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
	List<Product> getForPrintproducts(String searchKeyword, String order_by, int low_price, int high_price);

}
