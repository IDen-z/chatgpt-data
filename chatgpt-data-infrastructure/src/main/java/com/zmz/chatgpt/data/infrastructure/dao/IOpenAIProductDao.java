package com.zmz.chatgpt.data.infrastructure.dao;

import com.zmz.chatgpt.data.infrastructure.po.OpenAIProductPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 商品Dao
 * @create 2023-10-05 13:27
 */
@Mapper
public interface IOpenAIProductDao {

    OpenAIProductPO queryProductByProductId(Integer productId);

    List<OpenAIProductPO> queryProductList();

}
