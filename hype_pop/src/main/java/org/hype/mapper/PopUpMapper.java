package org.hype.mapper;

import java.util.List;
import java.util.Map; // 추가된 import
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.hype.domain.popStoreVO;

public interface PopUpMapper {
   public List<popStoreVO> getPopularPopUps();

   public List<String> getUserInterests(int userno);

   public List<popStoreVO> getTopStoresByInterest(@Param("value") String interest);
    
   public popStoreVO getStoreInfoByName(String storeName);

}
