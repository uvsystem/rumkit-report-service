package com.dbsys.rs.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dbsys.rs.report.entity.RekapKembali;

public interface RekapKembaliRepository extends JpaRepository<RekapKembali, Long> {

	@Query(nativeQuery = true,
			value = "SELECT s.id as id, s.pasien as pasien, b.nama as nama, "
				+ "b.penanggung as penanggung, b.harga as tarif, sum(s.jumlah) as jumlah, "
				+ "0 as tambahan "
				+ "from stok s "
				+ "inner join barang b on b.id = s.barang "
				+ "where s.pasien = :pasien "
				+ "group by s.barang")
	List<RekapKembali> rekap(@Param("pasien") Long pasien);

}
