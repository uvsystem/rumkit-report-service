package com.dbsys.rs.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dbsys.rs.report.entity.RekapPemakaian;

public interface RekapPemakaianRepository extends JpaRepository<RekapPemakaian, Long> {

	@Query(nativeQuery = true,
		value = "SELECT p.id as id, p.pasien as pasien, b.nama as nama, b.penanggung as penanggung, "
			+ "b.harga as tarif, sum(p.jumlah) as jumlah, sum(p.biaya_tambahan) as tambahan "
			+ "from pemakaian p "
			+ "inner join barang b on b.id = p.barang "
			+ "where p.pasien = :pasien "
			+ "group by p.barang")
	List<RekapPemakaian> rekap(@Param("pasien") Long pasien);
	
}
