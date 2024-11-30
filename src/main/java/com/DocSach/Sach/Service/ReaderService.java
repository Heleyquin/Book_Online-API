package com.DocSach.Sach.Service;

import com.DocSach.Sach.DTO.*;
import com.DocSach.Sach.Embeddable.Key_BinhLuan;
import com.DocSach.Sach.Embeddable.Key_CT_Goi_Dang_Ky;
import com.DocSach.Sach.Embeddable.Key_DanhGia;
import com.DocSach.Sach.Entity.*;
import com.DocSach.Sach.Responsitory.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReaderService {
    private final ReaderResponsive readerResponsive;
    private final AccountResponsi accountResponsi;
    private final LuotDocDocGiaReponsive luotDocDocGiaReponsive;
    private final SachResponsi sachResponsi;
    private final LichSuDocResponsi lichSuDocResponsi;
    private final CT_Dang_KyResponsi ct_dang_kyResponsi;
    private final LichSuMuaResponsi lichSuMuaResponsi;
    private final LichSuNapResponsi lichSuNapResponsi;
    private final SachMongMuonResponsi sachMongMuonResponsi;
    private final EntityManager entityManager;
    private final GoiDangKyResponsi goiDangKyResponsi;
    private final CmtResponsi cmtResponsi;
    private final DanhGiaResponsi danhGiaResponsi;

    public List<LuotDocDocGia> getLuotDocDocGia() {
        List<LuotDocDocGia> luotDocDocGias = luotDocDocGiaReponsive.findAll();
        return luotDocDocGias;
    }

    public ResponseEntity<?> getNguoiDung(long id) {
        Reader nguoiDung = readerResponsive.getReferenceById(id);
        return ResponseEntity.ok(ReaderResponse.builder()
                .id(nguoiDung.getId())
                .ten(nguoiDung.getTen())
                .ho(nguoiDung.getHo())
                .email(nguoiDung.getEmail())
                .gioiTinh(nguoiDung.isGioiTinh())
                .idAccount(nguoiDung.getAccount())
                .sachs(nguoiDung.getSachListFavor())
                .ngayTao(nguoiDung.getNgayTao())
                .lichSuDocs(nguoiDung.getLichSuDocs())
                .ctLichSuMua(nguoiDung.getCtMuaSach())
                .ctSachMongMuon(nguoiDung.getCtSachMongMuon())
                .ctGoiDangKys(nguoiDung.getCtGoiDangKySet())
                .build());
    }

    public ResponseEntity<?> getAllNguoiDung() {
        List<Reader> nguoiDungs = readerResponsive.findAll();
        return ResponseEntity.ok(nguoiDungs.stream().map(this::mapToNguoiDungResponse).toList());
    }

    public ReaderResponse mapToNguoiDungResponse(Reader nguoiDung) {
        return ReaderResponse.builder()
                .id(nguoiDung.getId())
                .ten(nguoiDung.getTen())
                .ho(nguoiDung.getHo())
                .email(nguoiDung.getEmail())
                .gioiTinh(nguoiDung.isGioiTinh())
                .ngayTao(nguoiDung.getNgayTao())
                .idAccount(nguoiDung.getAccount())
                .sachs(nguoiDung.getSachListFavor())
                .lichSuDocs(nguoiDung.getLichSuDocs())
                .ctGoiDangKys(nguoiDung.getCtGoiDangKySet())
                .ctSachMongMuon(nguoiDung.getCtSachMongMuon())
                .ctLichSuMua(nguoiDung.getCtMuaSach())
                .build();
    }

    public List<Reader> getReaderRencent(){
        LocalDateTime day = LocalDateTime.now().minusDays(7);
        return readerResponsive.findByNgayTaoAfter(day);
    }

    public ResponseEntity<?> getReaderByUsername(String username){
        Account account = accountResponsi.findByUsername(username);
        Reader reader = readerResponsive.findByAccount(account);
        return ResponseEntity.ok(ReaderResponse.builder()
                .id(reader.getId())
                .sachs(reader.getSachListFavor())
                .idAccount(reader.getAccount())
                .ten(reader.getTen())
                .ho(reader.getHo())
                .email(reader.getEmail())
                .gioiTinh(reader.isGioiTinh())
                .ngayTao(reader.getNgayTao())
                .lichSuDocs(reader.getLichSuDocs())
                .ctGoiDangKys(reader.getCtGoiDangKySet())
                .ctLichSuMua(reader.getCtMuaSach())
                .ctSachMongMuon(reader.getCtSachMongMuon())
                .build());
    }

    public ResponseEntity<?> addNguoiDung(ReaderRequest readerRequest) {
        Reader nguoiDung = Reader.builder()
                .ten(readerRequest.getTen())
                .ho(readerRequest.getHo())
                .gioiTinh(readerRequest.isGioiTinh())
                .email(readerRequest.getEmail())
                .account(readerRequest.getIdAccount())
                .sachListFavor(readerRequest.getSachs())
                .lichSuDocs(readerRequest.getLichSuDocs())
                .ctGoiDangKySet(readerRequest.getCtGoiDangKys())
                .ctMuaSach(readerRequest.getCtLichSuMua())
                .ctSachMongMuon(readerRequest.getCtSachMongMuon())
                .build();
        readerResponsive.save(nguoiDung);
        log.info("{} đã thêm", nguoiDung.getHo() + " " + nguoiDung.getTen());
        return new ResponseEntity<>("Thêm người dùng thành công!!!!!!!", HttpStatus.CREATED);
    }
    public ResponseEntity<?> deleteNguoiDung(long id) {
        try{
            Reader nguoiDung = readerResponsive.getReferenceById(id);
            long idNguoiDung = nguoiDung.getId();
            readerResponsive.delete(nguoiDung);
            log.info("{} đã xoá", idNguoiDung);
            return ResponseEntity.ok("Xoá thành công");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Người dùng không tồn tại");
        }
    }
    public ResponseEntity<?> updateNguoiDung(ReaderUpdate readerResponse) {
        try {
            Reader nguoiDungCu= readerResponsive.getReferenceById(readerResponse.getId());
            Reader nguoiDung = Reader.builder()
                    .id(readerResponse.getId())
                    .ten(readerResponse.getTen())
                    .ho(readerResponse.getHo())
                    .email(readerResponse.getEmail())
                    .gioiTinh(readerResponse.isGioiTinh())
                    .account(readerResponse.getIdAccount())
                    .sachListFavor(convertToSachEntities(readerResponse.getSachs()))
                    .lichSuDocs(convertToLichSuEntities(readerResponse.getLichSuDocs()))
                    .ngayTao(LocalDateTime.parse(readerResponse.getNgayTao()))
                    .ctGoiDangKySet(convertToCT_Goi_DangKyEntities(readerResponse.getCtGoiDangKys()))
                    .ctSachMongMuon(convertToMongMuon(readerResponse.getCtSachMongMuon()))
                    .ctMuaSach(convertToLichSuMua(readerResponse.getCtLichSuMua()))
                    .build();
            readerResponsive.save(nguoiDung);
            log.info("{} đã cập nhật thành công", nguoiDungCu.getHo() + " " + nguoiDungCu.getTen());
            return ResponseEntity.ok("Cập nhật thành công");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Không thành công, kiểm tra lại");
        }
    }

    private Set<Sach_Mong_Muon> convertToMongMuon(Set<Long> sachIds) {
        Set<Sach_Mong_Muon> sachSet = new HashSet<>();
        for (Long id : sachIds) {
            sachSet.add(sachMongMuonResponsi.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy sách")));
        }
        return sachSet;
    }

    public ResponseEntity<?> wishList(long id) {
        List<Sach_Mong_Muon> sachMongMuons = sachMongMuonResponsi.findAllByReaderWishId(id);
        return ResponseEntity.ok(sachMongMuons.stream().map(this::mapToWishListResponse).toList());
    }

    public SachMongMuonResponse mapToWishListResponse(Sach_Mong_Muon sachMongMuon) {
        return SachMongMuonResponse.builder()
                .readerWish(sachMongMuon.getReaderWish())
                .sachWish(sachMongMuon.getSachWish())
                .id(sachMongMuon.getId())
                .build();
    }

    private Set<LichSuMua> convertToLichSuMua(Set<Long> sachIds) {
        Set<LichSuMua> sachSet = new HashSet<>();
        for (Long id : sachIds) {
            sachSet.add(lichSuMuaResponsi.findById(id).orElseThrow(() -> new RuntimeException("Không có lịch sử")));
        }
        return sachSet;
    }

    private Set<Sach> convertToSachEntities(Set<Long> sachIds) {
        Set<Sach> sachSet = new HashSet<>();
        for (Long id : sachIds) {
            sachSet.add(sachResponsi.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy sách")));
        }
        return sachSet;
    }
    private Set<LichSuDoc> convertToLichSuEntities(Set<Long> lichSuIds) {
        Set<LichSuDoc> lichSuDocs = new HashSet<>();
        for (Long id : lichSuIds) {
            lichSuDocs.add(lichSuDocResponsi.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy lịch sử")));
        }
        return lichSuDocs;
    }
    private Set<CT_Dang_Ky> convertToCT_Goi_DangKyEntities(Set<Key_CT_Goi_Dang_Ky> key_ct_goi_dang_kies){
        Set<CT_Dang_Ky> ct_dang_kies = new HashSet<>();
        for(Key_CT_Goi_Dang_Ky id : key_ct_goi_dang_kies){
            ct_dang_kies.add(ct_dang_kyResponsi.findById(id).orElseThrow(() -> new RuntimeException("Không thấy cmt")));
        }
        return ct_dang_kies;
    }

    public ResponseEntity<?> napTien(LichSuNapRequest lichSuNapRequest) {
        Reader reader = readerResponsive.getReferenceById(lichSuNapRequest.getReaderId());
        LichSuNap lichSuNap = LichSuNap.builder()
                .tien(lichSuNapRequest.getTien())
                .reader(reader)
                .build();
        lichSuNapResponsi.save(lichSuNap);
        log.info("{} đã nạp", lichSuNap.getTien());
        return new ResponseEntity<>("Nạp thành công!!!!!!!", HttpStatus.CREATED);
    }

    public ResponseEntity<?> muaSach(LichSuMuaRequest lichSuMuaRequest) {
        LichSuMua lichSuMua = LichSuMua.builder()
                .giaTien(lichSuMuaRequest.getGiaTien())
                .sachBuy(lichSuMuaRequest.getSachBuy())
                .readerBuy(lichSuMuaRequest.getReaderBuy())
                .build();
        lichSuMuaResponsi.save(lichSuMua);
        return new ResponseEntity<>("Mua thành công!!!!!!!", HttpStatus.CREATED);
    }
    public ResponseEntity<?> getLichSuMua(long id) {
        List<LichSuMua> lichSuMuas = lichSuMuaResponsi.findAllByReaderBuyId(id);
        return ResponseEntity.ok(lichSuMuas.stream().map(this::mapToLichSuMuaResponse).toList());
    }

    public LichSuMuaResponse mapToLichSuMuaResponse(LichSuMua lichSuMua) {
        return LichSuMuaResponse.builder()
                .giaTien(lichSuMua.getGiaTien())
                .id(lichSuMua.getId())
                .thoiGianMua(lichSuMua.getThoiGianMua())
                .readerBuy(lichSuMua.getReaderBuy())
                .sachBuy(lichSuMua.getSachBuy())
                .build();
    }

    public ResponseEntity<?> getLichSuNap(Long id) {
        Reader reader = readerResponsive.getReferenceById(id);
        List<LichSuNap> lichSuNaps = lichSuNapResponsi.findAllByReader(reader);
        return ResponseEntity.ok(lichSuNaps.stream().map(this::mapToLichSuNapResponse).toList());
    }

    public LichSuNapResponse mapToLichSuNapResponse(LichSuNap lichSuNap) {
        return LichSuNapResponse.builder()
                .id(lichSuNap.getId())
                .reader(lichSuNap.getReader())
                .thoiGianNap(lichSuNap.getThoiGianNap())
                .tien(lichSuNap.getTien())
                .build();
    }

    public Double getAmount(Long id){
        return readerResponsive.totalAmountAvailable(id);
    }

    public Object[] callSP(Long idReader){
        try{
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_get_goi_dang_ki");
            query.registerStoredProcedureParameter("id_reader", Long.class, ParameterMode.IN);
            query.setParameter("id_reader", idReader);
            query.execute();
            Object[] result = (Object[]) query.getSingleResult();
            return result;
        }catch(Exception e){
            return null;
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<?> buyAll(List<LichSuMuaRequest> lichSuMuaRequests){
        for(LichSuMuaRequest lichSuMuaRequest : lichSuMuaRequests){
            LichSuMua lichSuMua = LichSuMua.builder()
                    .readerBuy(lichSuMuaRequest.getReaderBuy())
                    .sachBuy(lichSuMuaRequest.getSachBuy())
                    .giaTien(lichSuMuaRequest.getGiaTien())
                    .build();
            lichSuMuaResponsi.save(lichSuMua);
        }
        return ResponseEntity.ok("Thêm thành công");
    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<?> delAll(List<Long> wishIds){
        for(Long wishId : wishIds){
            Sach_Mong_Muon sachMongMuon = sachMongMuonResponsi.getReferenceById(wishId);
            sachMongMuonResponsi.delete(sachMongMuon);
        }
        return ResponseEntity.ok("Xoá thành công giỏ");
    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<?> purchase(List<LichSuMuaRequest> lichSuMuaRequests, List<Long> wishIds) {
        for (LichSuMuaRequest lichSuMuaRequest : lichSuMuaRequests) {
            LichSuMua lichSuMua = LichSuMua.builder()
                    .readerBuy(lichSuMuaRequest.getReaderBuy())
                    .sachBuy(lichSuMuaRequest.getSachBuy())
                    .giaTien(lichSuMuaRequest.getGiaTien())
                    .build();
            lichSuMuaResponsi.save(lichSuMua);
        }

        for (Long wishId : wishIds) {
            Sach_Mong_Muon sachMongMuon = sachMongMuonResponsi.getReferenceById(wishId);
            sachMongMuonResponsi.delete(sachMongMuon);
        }
        return ResponseEntity.ok("Mua thành công");
    }
    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<?> purchaseOne(LichSuMuaRequest lichSuMuaRequest){
        Sach_Mong_Muon sachMongMuon = sachMongMuonResponsi.findSach_Mong_MuonByReaderWishIdAndSachWishId(lichSuMuaRequest.getReaderBuy().getId(), lichSuMuaRequest.getSachBuy().getId());
        if(sachMongMuon != null){
            sachMongMuonResponsi.delete(sachMongMuon);
        }
        LichSuMua lichSuMua = LichSuMua.builder()
                .readerBuy(lichSuMuaRequest.getReaderBuy())
                .sachBuy(lichSuMuaRequest.getSachBuy())
                .giaTien(lichSuMuaRequest.getGiaTien())
                .build();
        lichSuMuaResponsi.save(lichSuMua);
        return ResponseEntity.ok("Mua thành công");
    }
    public ResponseEntity<?> purchasePack(CT_DangKyRequest ctDangKyRequest){
        GoiDangKy goiDangKy = goiDangKyResponsi.getReferenceById(ctDangKyRequest.getMaGoi());
        Reader reader = readerResponsive.getReferenceById(ctDangKyRequest.getIdReader());
        Key_CT_Goi_Dang_Ky keyCtGoiDangKy = new Key_CT_Goi_Dang_Ky(ctDangKyRequest.getIdReader(), ctDangKyRequest.getMaGoi(), LocalDateTime.now());
        CT_Dang_Ky ctDangKy = CT_Dang_Ky.builder()
                .goiDangKy(goiDangKy)
                .readerDangKy(reader)
                .id(keyCtGoiDangKy)
                .giaTien(ctDangKyRequest.getGiaTien())
                .build();
        ct_dang_kyResponsi.save(ctDangKy);
        return ResponseEntity.ok("Đăng ký thành công");
    }

    public ResponseEntity<?> comment(CmtRequest cmtRequest){
        Sach sach = sachResponsi.getReferenceById(cmtRequest.getId().getIdSach());
        Reader reader = readerResponsive.getReferenceById(cmtRequest.getId().getId_DocGia());
        Key_BinhLuan keyBinhLuan = new Key_BinhLuan(cmtRequest.getId().getIdSach(), cmtRequest.getId().getId_DocGia(), LocalDateTime.now());
        Cmt cmt = Cmt.builder()
                .id(keyBinhLuan)
                .readerCmt(reader)
                .sachCmt(sach)
                .noiDung(cmtRequest.getNoiDung())
                .build();
        cmtResponsi.save(cmt);
        return ResponseEntity.ok("Cmt thành công");
    }

    public CT_DangKyResponse mapToCTDKResponse(CT_Dang_Ky ctDangKy) {
        return CT_DangKyResponse.builder()
                .reader(ctDangKy.getReaderDangKy())
                .goiDangKy(ctDangKy.getGoiDangKy())
                .id(ctDangKy.getId())
                .giaTien(ctDangKy.getGiaTien())
                .build();
    }

    public ResponseEntity<?> allPackPurchase(Long id){
        List<CT_Dang_Ky> ct_dang_kies = ct_dang_kyResponsi.findAllByReaderDangKyId(id);
        return ResponseEntity.ok(ct_dang_kies.stream().map(this::mapToCTDKResponse).toList());
    }
    public ResponseEntity<?> danhGia(DanhGiaRequest danhGiaRequest){
        Sach sach = sachResponsi.getReferenceById(danhGiaRequest.getId().getIdSach());
        Reader reader = readerResponsive.getReferenceById(danhGiaRequest.getId().getId_DocGia());
        Key_DanhGia keyDanhGia = new Key_DanhGia(danhGiaRequest.getId().getIdSach(), danhGiaRequest.getId().getId_DocGia());
        DanhGia danhGia = DanhGia.builder()
                .id(keyDanhGia)
                .readerRate(reader)
                .sachRate(sach)
                .point(danhGiaRequest.getPoint())
                .nhanXet(danhGiaRequest.getNhanXet())
                .build();
        danhGiaResponsi.save(danhGia);
        return ResponseEntity.ok("Rate done!!!");
    }
    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<?> rateWithComment(RateAndCmt rateAndCmt){
        this.danhGia(rateAndCmt.getDanhGiaRequest());
        this.comment(rateAndCmt.getCmtRequest());
        return ResponseEntity.ok("Done!!!");
    }
    public ResponseEntity<?> getRateByUserAndBook(Key_DanhGia keyDanhGia){
        DanhGia danhGia = danhGiaResponsi.findDanhGiaById(keyDanhGia);
//        Key_DanhGia keyDanhGia = new Key_DanhGia(danhGiaKey.getIdSach(), danhGiaKey.getId_DocGia());
        return ResponseEntity.ok(DanhGIaResponse.builder()
                .id(keyDanhGia)
                .nhanXet(danhGia.getNhanXet())
                .point(danhGia.getPoint())
                .thoiGianCapNhat(danhGia.getThoiGianCapNhat())
                .thoiGianTao(danhGia.getThoiGianTao())
                .readerRate(danhGia.getReaderRate())
                .sachRate(danhGia.getSachRate())
                .build());
    }
}
