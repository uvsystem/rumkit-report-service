package com.dbsys.rs.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dbsys.rs.report.entity.RekapPelayanan;

public interface RekapPelayananRepository extends JpaRepository<RekapPelayanan, Long> {

	@Query(nativeQuery = true,
		value = "SELECT p.id as id, p.pasien as pasien, u.nama as unit, u.tipe as tipe_unit, t.nama as nama, "
			+ "t.penanggung as penanggung, sum(p.jumlah) as jumlah, t.tarif as tarif, sum(p.biaya_tambahan) as tambahan "
			+ "from pelayanan p "
			+ "inner join tindakan t on t.id = p.tindakan "
			+ "inner join unit u on u.id = p.unit "
			+ "where p.pasien = :pasien "
			+ "group by p.tindakan, u.tipe")
	List<RekapPelayanan> rekap(@Param("pasien") Long pasien);
	
}
