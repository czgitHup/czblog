package com.cz.blogtwo.entity;

import lombok.*;

/**
 * @Description: 城市访客数量
 * @Author: Naccl
 * @Date: 2021-02-26
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CityVisitor {
	private String city;//城市名称
	private Integer uv;//独立访客数量
}
