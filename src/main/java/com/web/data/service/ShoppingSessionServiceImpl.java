package com.web.data.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.web.cart.CartDetail;
import com.web.cart.CartDetail.CartDetailQuantityIsZero;
import com.web.cart.ShoppingSession;
import com.web.data.CartDetailRepository;
import com.web.data.ShoppingSessionRepository;
import com.web.data.UserRepository;
import com.web.product.Product;
import com.web.user.User;
import com.web.util.DateTimeUtil;

@Service("sservice")
public class ShoppingSessionServiceImpl implements ShoppingSessionService {

//	private ShoppingSessionRepository ssDao;
//	
//	@Autowired
//	public ShoppingSessionServiceImpl(ShoppingSessionRepository ssrepo) {
//		ssDao = ssrepo;
//	}

//	
	@Autowired
	@Qualifier("ssrepo")
	ShoppingSessionRepository ssDao;

	@Autowired
	@Qualifier("urepo")
	UserRepository uDao;

	@Autowired
	@Qualifier("cartdrepo")
	CartDetailRepository cartDao;

	@Override
	public boolean updateSession(String sessionId, User u) {

		System.out.println("Update session id: " + sessionId + "-> " + u);
//		ssDao.addSessionOfUser();

//		ssD
		try {
			ssDao.updateSession(sessionId, u.getId());
			ShoppingSession ss = ssDao.findByCurSession(sessionId).orElse(null);
			System.out.println("finish update: " + ss);

			return true;
		} catch (Exception ex) {
			System.out.println("why false");
			ex.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean updateSession(String sessionId, Integer u) {

		User user = uDao.findById(u).orElse(null);

		if (user == null) {
			throw new RuntimeException("Khong ton tai user voi ID " + u);
		}
		return updateSession(sessionId, user);

	}

	@Override
	public Optional<ShoppingSession> findByUserID(Integer uid) {
		ShoppingSession ss = null;
		try {
			User user = uDao.findById(uid).orElse(null);
//			ss = ssDao.findByUser(user).orElse(null);	
			return ssDao.findByUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Can't find ss with uid " + uid);
		}

	}

	@Override
	public List<ShoppingSession> findAll() {
		return ssDao.findAll();
//		return ssDao.findAll();
	}

	@Override
	public List<CartDetail> findCartDetailWithSession(String session) {
		ShoppingSession shopsession = ssDao.findByCurSession(session).orElse(null);

		if (shopsession == null)
			return null;

		return shopsession.getCd();
	}

	@Override
	public Optional<ShoppingSession> findBySession(String session) {
		// TODO Auto-generated method stub
		return ssDao.findByCurSession(session);
	}

	@Override
	public boolean addProductToCart(Integer proId, String session) {

		ShoppingSession ss = ssDao.findByCurSession(session).orElse(null);
		Product newp = cartDao.getProduct(proId).orElse(null);

		try {
			if (ss == null || newp == null)
				throw new RuntimeException("Khong tim thay session shopping or product");

			Integer sid = ss.getId();

//			boolean isIn = isInCart(proId, ss.getCd());
			CartDetail cartRow = findProductInCart(sid, proId).orElse(null);
			boolean isIn = cartRow != null;

			System.out.println("isIn " + isIn);

			System.out.println("sid " + sid + "prodID " + proId);
			if (isIn) {

				cartRow.increase();
				System.out.println("increase quan");
				cartDao.save(cartRow);
				return true;
//				for(CartDetail cd : ss.getCd()) {
//					if(cd.getProduct().getId().equals(proId))  {
//						System.out.println("increase quan");
//						cd.increase();
//						cartDao.save(cd);
//						return true;
//					}
//				}
			} else {
				CartDetail ncd = new CartDetail();
				ncd.setCreTime(DateTimeUtil.asCurrentDate());
				ncd.setRepTime(DateTimeUtil.asCurrentDate());
				ncd.setProduct(newp);
				ncd.setShopSession(ss);
				ncd.setQuantity(1);

				System.out.println("add new product");
				cartDao.save(ncd);
				return true;
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("addProductToCart can't add");
		}
	
	}

@Override	
	public boolean reduceProductInCart(Integer proId, String session) {
		ShoppingSession ss = ssDao.findByCurSession(session).orElse(null);
		Product newp = cartDao.getProduct(proId).orElse(null);

		try {
			if (ss == null || newp == null)
				throw new RuntimeException("Khong tim thay session shopping or product");

			Integer sid = ss.getId();

//			boolean isIn = isInCart(proId, ss.getCd());
			CartDetail cartRow = findProductInCart(sid, proId).orElse(null);
			boolean isIn = cartRow != null;

			System.out.println("isIn " + isIn);

			System.out.println("sid " + sid + "prodID " + proId);
			if (isIn) {
				try {
					cartRow.reduce();
				}catch (CartDetailQuantityIsZero e) {	
					cartDao.delete(cartRow);
					return true;
				}
				
				cartDao.save(cartRow);
				return true;
			} else {
				return false;
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("reduce can't add");
		}
	}
	
	public Optional<CartDetail> findProductInCart(Integer sid, Integer proId) {
		return cartDao.findProductInCart(sid, proId);
	}

	public boolean isInCart(Integer id, List<CartDetail> list) {
		for (CartDetail cd : list) {
			if (cd.getProduct().getId().equals(id))
				return true;

		}
		return false;
	}

	
//	Thay bằng CartDetai Object
	@Override
	public boolean updateProductInCart(Integer proId, Integer qty, String session) {
			
		Optional<ShoppingSession> shopsession = ssDao.findByCurSession(session);
		try {
			if(shopsession.isEmpty()) throw new RuntimeException("Không có session tương ứng");
			 
			Integer sid  = shopsession.get().getId();
			
			CartDetail  cd =  findProductInCart(sid, proId).orElseThrow(
				() -> new RuntimeException("Không có sản phẩm tương ứng trong giỏ"));
			
			if(qty == null || qty < 0 ) {
				throw new RuntimeException("Số lượng không thích hợp");
			}else {
				if(qty == 0) cartDao.delete(cd);
				else {
					cd.setQuantity(qty);
					cartDao.save(cd);
				}
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	public void clearCart(Integer userId) {
		ssDao.clearCart(userId);
		
	}
	
	
	


}
