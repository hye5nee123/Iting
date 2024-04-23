package com.iting.tlsn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iting.lecture.mapper.CurriMapper;
import com.iting.lecture.model.CurriVO;
import com.iting.tlsn.mapper.TlsnMapper;
import com.iting.tlsn.model.TlsnDetailVO;
import com.iting.tlsn.model.TlsnVO;
import com.iting.tlsn.service.TlsnService;

@Service
public class TlsnServiceImpl implements TlsnService {

	@Autowired
	TlsnMapper tlsnMapper;
	
	@Autowired
	CurriMapper curriMapper;
	
	@Override
	public List<TlsnVO> getTlsnList(String user) {
		return tlsnMapper.getTlsnList(user);
	}

	@Override
	public List<TlsnVO> getTlsnDetailList(String ltNum, String user) {
		return tlsnMapper.getTlsnDetailList(ltNum, user);
	}

	@Override
	public TlsnVO getTlsnInfo(TlsnVO vo) {
		return tlsnMapper.getTlsnInfo(vo);
	}

	@Override
	public int tlsnInsert(TlsnVO vo) {
		
		int tlsnResult = tlsnMapper.tlsnInsert(vo);
		
		// 수강등록
		if(tlsnResult > 0) {
			
			// 커리큘럼 회차 번호
			CurriVO cvo = CurriVO.builder()
								 .ltNum(vo.getLtNum())
								 .build();
			
			List<CurriVO> clist = curriMapper.getCurriList(cvo);
			
			String rndNum = "";
			TlsnDetailVO tdvo = null;
			
			// 커리큘럼 회차 만큼 수강 상세 진도율 초기 등록
			for(CurriVO curri : clist) {
				rndNum = curri.getRndNum(); // 회차 번호 - 커리큘럼
				
				tdvo = TlsnDetailVO.builder()
									.ltNum(vo.getLtNum())
									.tlsnNum(vo.getTlsnNum())
									.rndNum(rndNum)
									.rndPrgre(0.0)
									.build();
				
				// 수강 상세 초기 등록
				tlsnMapper.tlsnDetailInsert(tdvo);
			}
			return 1;
		};
		return -1;
	}

	@Override
	public TlsnVO getTlsnInfoMem(TlsnVO vo) {
		return tlsnMapper.getTlsnInfoMem(vo);
	}
	
	@Override
	public int tlsnDetailInsert(TlsnDetailVO vo) {
		return tlsnMapper.tlsnDetailInsert(vo);
	}

	@Override
	public int tlsnDetailUpdate(TlsnDetailVO vo) {
		return tlsnMapper.tlsnDetailUpdate(vo);
	}

	@Override
	public TlsnDetailVO getTlsnDetailInfo(String rndNum) {
		return tlsnMapper.getTlsnDetailInfo(rndNum);
	}

	@Override
	public List<TlsnDetailVO> getTlsnDtCurriInfo(String rndNum) {
		return tlsnMapper.getTlsnDtCurriInfo(rndNum);
	}

	
}
