package org.jae.mapper;

import java.util.List;

import org.jae.domain.BoardAttachVO;

public interface BoardAttachMapper {
       public void insert(BoardAttachVO avo);
       public void delete(String uuid);
       public List<BoardAttachVO> findByBno(int bno);
}
