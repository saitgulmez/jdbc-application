
select * from Musteri 
select * from Sepet
select * from Urun	

# Musteri, Sepet ve Urun join edip, kimin kaç tane sepete sahip olduğunu 
# ve ürün tutarlarının toplamını veren query
# Hiç efficent bir query değil çünkü 3 tane tabloyu aynı anda join ediyoruz. 
# Daha etkili bir query bulmalıyız.

SELECT M.ad AS Ad, COUNT(U1.urunId) AS SepetSayisi, SUM(U1.tutar) AS Tutar
FROM Musteri M, Sepet S1, Urun U1
WHERE M.musteriId = S1.musteriId AND S1.sepetId = U1.sepetId
GROUP  M.ad


# Musteri, Sepet ve Urun join ediyoruz.
# Output olarak hangi şehir toplamda kaç sepette bilgisi mevcut ve toplam ürün tutarlarının kaç olduğunu 
# bize sonuç olarak dönüyor.

SELECT M.sehir AS Şehir, COUNT(S1.sepetId) AS ToplamSepetSayisi, SUM(U1.tutar) AS ToplamTutar
FROM Musteri M, Sepet S1, Urun U1
WHERE M.musteriId = S1.musteriId AND S1.sepetId = U1.sepetId
	-- and 
	-- S1.sepetId in ( select U2.sepetId
	-- 				from Sepet S2, Urun U2
	-- 				where S2.sepetId = U2.sepetId and S1.musteriId = S2.musteriId )
GROUP  M.sehir
ORDER  M.sehir




# Hangi müşteriId = 4 olan birinin hangi şehirde olduğu ve toplamda kaç tane sepet te kaydı olduğunu 
# sonuç dönen query
SELECT  M.ad, M.sehir, COUNT(S.sepetId) 
FROM Musteri M, Sepet S
WHERE M.musteriId = S.musteriId AND M.musteriId = 4
GROUP  sehir








/*SELECT M.sehir, Count(S1.sepetId) AS SepetSayisi  
FROM Musteri M, Sepet S1  
WHERE M.musteriId = S1.musteriId  
AND S1.musteriId  IN   (SELECT S2.musteriId   
						FROM Sepet S2, Urun U1  
						WHERE S2.sepetId = U1.sepetId)
UNION  
SELECT Sum(U.tutar) AS ToplamTutar, U.urunId  
FROM Urun U, Sepet S1  
WHERE U.sepetId = S1.sepetId  
AND S1.musteriId  IN (SELECT S2.musteriId 
			FROM Musteri M, Sepet S2  
			WHERE S2.musteriId = M.musteriId)
GROUP BY M.sehir  
ORDER BY M.sehir*/


