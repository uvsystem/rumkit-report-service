package com.dbsys.rs.report.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbsys.rs.report.entity.RekapPelayanan;
import com.dbsys.rs.report.entity.RekapPemakaian;
import com.dbsys.rs.report.entity.RekapStokBarang;
import com.dbsys.rs.report.entity.RekapTagihan;
import com.dbsys.rs.report.entity.RekapUnit;
import com.dbsys.rs.report.repository.RekapPemakaianRepository;
import com.dbsys.rs.report.repository.RekapStokRepository;
import com.dbsys.rs.report.repository.RekapPelayananRepository;
import com.dbsys.rs.report.repository.RekapUnitRepository;

@Service
@Transactional(readOnly = true)
public class ReportServiceImpl implements ReportService {

	@Autowired
	private RekapUnitRepository rekapUnitRepository;
	@Autowired
	private RekapStokRepository rekapStokRepository;
	@Autowired
	private RekapPelayananRepository rekapPelayananRepository;
	@Autowired
	private RekapPemakaianRepository rekapPemakaianRepository;
	
	@Override
	public List<RekapUnit> rekapUnit(Date awal, Date akhir) {
		return rekapUnitRepository.rekap(awal, akhir);
	}

	@Override
	public List<RekapStokBarang> rekapStok(Date awal, Date akhir) {
		return rekapStokRepository.rekap(awal, akhir);
	}

	@Override
	public List<RekapTagihan> rekapTagihan(Long pasien) {
		List<RekapPelayanan> listPelayanan = rekapPelayananRepository.rekap(pasien);
		List<RekapPemakaian> listPemakaian = rekapPemakaianRepository.rekap(pasien);

		List<RekapTagihan> listTagihan = new ArrayList<>();
		listTagihan.addAll(listPelayanan);
		listTagihan.addAll(listPemakaian);
		
		return listTagihan;
	}
}
