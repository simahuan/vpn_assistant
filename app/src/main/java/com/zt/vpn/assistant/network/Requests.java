package com.zt.vpn.assistant.network;

import com.android.volley.Response;

import org.json.JSONObject;

public class Requests {

    public static Object getHomeAds(Response.Listener<JSONObject> l, Response.ErrorListener e) {
        ParamBuilder b = ParamBuilder.newInstance().setApiName("Base.GetAdSettingAreaList");
        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
        return RequestManager.add(r);
    }

//    public static Object getHeadlines(Paging paging, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("CmsArticle.GetHeadLineList")
//                .put("pageNo", paging.getNextPageIndexStartWith1())
//                .put("pageSize", paging.getPageSize());
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getHeadline(String id, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("CmsArticle.GetHeadLine")
//                .put("SysNo", id);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getRecommendations(Paging paging, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Product.QueryRecommendedGood")
//                .put("pageNo", paging.getNextPageIndexStartWith1())
//                .put("pageSize", paging.getPageSize());
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getCategories(Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Category.GetCategoryList");
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getItemDetail(String accessToken, String itemId, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Product.GetSKUDetail")
//                .putOmitEmpty("AccessToken", accessToken)
//                .put("skuSysNo", itemId);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getCoupon(String accessToken, String couponId, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Member.ReceiveCoupon")
//                .put("AccessToken", accessToken)
//                .put("CouponSysNo", couponId);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getCouponListForOrder(String accessToken, ConfirmOrder cart, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        JSONArray array = new JSONArray();
//        try {
//            JSONObject o = new JSONObject();
//            o.put("ShopSysNo", cart.getShopId());
//            List<JSONObject> itemIds = new ArrayList<>();
//            JSONObject tmp;
//            for (ConfirmOrderItem item : cart.getItems()) {
//                tmp = new JSONObject();
//                tmp.put("WareNum", item.getCount());
//                tmp.put("WareSysNo", item.getId());
//                itemIds.add(tmp);
//            }
//            o.put("WareList", new JSONArray(itemIds));
//            array.put(o);
//        } catch (JSONException ignored) {
//        }
//
//        JsonParamBuilder b = JsonParamBuilder.newInstance()
//                .setApiName("Member.GetCouponByShopProducts")
//                .put("AccessToken", accessToken)
//                .put("ShopProducts", array)
//                .put("TotalAmount", FormatUtils.getCurrencyNoSymbol(cart.getTotalPrice()));
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getCouponListForUser(String accessToken, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Member.GetUserAvailableCoupons")
//                .put("AccessToken", accessToken);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getPromotions(String itemId, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Product.GetProductGifts")
//                .put("WareSysNo", itemId);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getInstallment(double price, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Product.GetHccInstalmentsInfo")
//                .put("SalePrice", FormatUtils.getCurrencyNoSymbol(price));
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object addStar(String accessToken, ItemDetail itemDetail, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Member.AddUserCollect")
//                .put("AccessToken", accessToken)
//                .put("ShopSysNo", itemDetail.getShopId())
//                .put("Type", 0)
//                .put("ProductSkuNo", itemDetail.getId())
//                .put("Price", FormatUtils.getCurrencyNoSymbol(itemDetail.getPrice()));
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object addBrowseHistory(String accessToken, ItemDetail itemDetail, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Member.AddUserCollect")
//                .put("AccessToken", accessToken)
//                .put("ShopSysNo", itemDetail.getShopId())
//                .put("Type", 2)
//                .put("ProductSkuNo", itemDetail.getId())
//                .put("Price", FormatUtils.getCurrencyNoSymbol(itemDetail.getPrice()));
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object removeStar(String accessToken, ItemDetail itemDetail, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Member.CancleUserCollectBySku")
//                .put("AccessToken", accessToken)
//                .put("Type", 0)
//                .put("SkuSysNo", itemDetail.getId())
//                .put("ShopSysNo", itemDetail.getShopId());
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object queryCart(String accessToken, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Member.QueryShoppingCart")
//                .put("AccessToken", accessToken);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object addItemIntoCart(String accessToken, CartItem item, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        List<CartItem> items = new ArrayList<>();
//        items.add(item);
//        return addItemsIntoCart(accessToken, items, l, e);
//    }
//
//    public static Object addItemsIntoCart(String accessToken, List<CartItem> items, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        JSONArray array = new JSONArray();
//        for (CartItem cartItem : items) {
//            JSONObject item = new JSONObject();
//            try {
//                item.put("SkuSysNo", cartItem.getItemId());
//                item.put("ShopSysNo", cartItem.getShopId());
//                item.put("ProductNum", cartItem.getCount());
//            } catch (JSONException ignored) {
//            }
//            array.put(item);
//        }
//        JsonParamBuilder b = JsonParamBuilder.newInstance()
//                .setApiName("Member.AddShoppingCart")
//                .put("AccessToken", accessToken)
//                .put("AddShopingCarts", array);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object deleteItemFromCart(String accessToken, String id, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        List<String> ids = new ArrayList<>();
//        ids.add(id);
//        return deleteItemsFromCart(accessToken, ids, l, e);
//    }
//
//    public static Object deleteItemsFromCart(String accessToken, List<String> ids, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        JSONArray array = new JSONArray(ids);
//        JsonParamBuilder b = JsonParamBuilder.newInstance()
//                .setApiName("Member.DeleteShoppingCart")
//                .put("AccessToken", accessToken)
//                .put("OperateType", 0)
//                .put("CartDetailSysNo", array);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object updateItemInCart(String accessToken, String id, int count, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Member.UpdateShoppingCart")
//                .put("AccessToken", accessToken)
//                .put("CartDetailSysNo", id)
//                .put("ProductNum", count);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getProperties(String spuId, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Product.GetSpuAllSpecPropertys")
//                .put("SpuSysNo", spuId);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object changeCartItem(String accessToken, CartItem cartItem, String itemId, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Member.UpdateShoppingCartSku")
//                .put("AccessToken", accessToken)
//                .put("CartDetailSysNo", cartItem.getId())
//                .put("SkuSysNo", itemId)
//                .put("ShopSysNo", cartItem.getShopId())
//                .put("ProductNum", cartItem.getCount());
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object checkStock(String accessToken, List<CartItem> items, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        JSONArray array = new JSONArray();
//        for (CartItem cartItem : items) {
//            JSONObject item = new JSONObject();
//            try {
//                item.put("SkuSysNo", cartItem.getItemId());
//                item.put("BuyNum", cartItem.getCount());
//            } catch (JSONException ignored) {
//            }
//            array.put(item);
//        }
//        JsonParamBuilder b = JsonParamBuilder.newInstance()
//                .setApiName("Member.CheckStock")
//                .put("AccessToken", accessToken)
//                .put("ShopingCartList", array);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getOrder(String accessToken, List<CartItem> items, boolean isBuyInAdvance, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        JSONArray array = new JSONArray();
//        for (CartItem cartItem : items) {
//            JSONObject item = new JSONObject();
//            try {
//                item.put("SkuSysNo", cartItem.getItemId());
//                item.put("BuyNum", cartItem.getCount());
//            } catch (JSONException ignored) {
//            }
//            array.put(item);
//        }
//        JsonParamBuilder b = JsonParamBuilder.newInstance()
//                .setApiName("member.GetBalanceProduct")
//                .put("AccessToken", accessToken)
//                .put("IsImmediatelyBuy", isBuyInAdvance ? 1 : 0)
//                .put("ShopingCartList", array);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object submitOrder(String accessToken, ConfirmOrderInfo info, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        JsonParamBuilder b = info.toParamBuilder().put("AccessToken", accessToken);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object queryOrders(String accessToken, int orderStatus, int pageIndex, int pageSize, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Order.OrderPageList")
//                .put("AccessToken", accessToken)
//                .put("OrderStatus", orderStatus)
//                .put("PageNo", pageIndex)
//                .put("PageSize", pageSize);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getOrderDetail(String accessToken, String orderId, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Order.OrderDetail")
//                .put("AccessToken", accessToken)
//                .put("OrderSysNo", orderId);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object cancelOrder(String accessToken, String orderId, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Order.OrderCancel")
//                .put("AccessToken", accessToken)
//                .put("OrderSysNo", orderId);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object deleteOrder(String accessToken, String orderId, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Order.DeleteOrder")
//                .put("AccessToken", accessToken)
//                .put("OrderSysNo", orderId);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object confirmOrder(String accessToken, String orderId, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Order.OrderConfirmReceipt")
//                .put("AccessToken", accessToken)
//                .put("OrderSysNo", orderId);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getLogisticsDetail(String accessToken, String logisticsId, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Order.GetLogisticsDetail")
//                .put("AccessToken", accessToken)
//                .put("WayBillNumber", logisticsId);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object checkAccountAvailability(String phone, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("User.CheckUser")
//                .put("Account", phone)
//                .put("LoginType", 2);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object sendVerificationCode(String phone, int type,
//                                              Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("User.SendPhoneVerifyCode")
//                .put("Phone", phone)
//                .put("Type", type);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//
//    public static Object register(String phone, String verificationCode, String pwd,
//                                  Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("User.UserRegister")
//                .put("Phone", phone)
//                .put("Password", SecurityUtils.encrypt(pwd))
//                .put("VerificationCode", verificationCode)
//                .put("OrderSource", 0);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object checkVerificationCode(String phone, String code, int type,
//                                               Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("User.CheckPhoneCode")
//                .put("Phone", phone)
//                .put("VerifyCode", code)
//                .put("Type", type);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object resetPassword(String phone, String pwd, String verificationCode,
//                                       Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("User.ResetPwd")
//                .put("Phone", phone)
//                .put("Password", SecurityUtils.encrypt(pwd))
//                .put("VerificationCode", verificationCode);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object login(String phone, String pwd,
//                               Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("User.UserLogin")
//                .put("Phone", phone)
//                .put("Password", SecurityUtils.encrypt(pwd));
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getCaptcha(Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("User.GetVerifyCode");
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getTrends(Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Marketing.QueryHotWordList")
//                .put("DataType", 1);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getItems(SearchOptions options, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        JsonObjectRequest r = new JsonObjectRequest(options.toParamBuilder().build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getProfileDetail(String token, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("User.GetUserInfo")
//                .put("AccessToken", token);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object modifyProfile(String token, Profile p, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("User.ModifyUserInfo")
//                .put("AccessToken", token)
//                .put("Gender", p.getGender())
//                .put("BirthDate", p.getBirthday());
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object modifyPassword(String token, String old, String n,
//                                        Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("User.ModifyUserPassword")
//                .put("AccessToken", token)
//                .put("OldPassword", SecurityUtils.encrypt(old))
//                .put("NewPassword", SecurityUtils.encrypt(n));
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getAddresses(String token, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("User.QueryRecvAddress")
//                .put("AccessToken", token);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object removeAddress(String token, String id,
//                                       Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        //noinspection SpellCheckingInspection
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("User.DeleteRecvAddress")
//                .put("AccessToken", token)
//                .put("AddressId", id);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object newAddress(String token, Address a,
//                                    Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        //noinspection SpellCheckingInspection
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("User.AddRecvAddress")
//                .put("AccessToken", token)
//                .put("Name", a.getName())
//                .put("Phone", a.getPhone())
//                .put("AreaCode", a.getAreaCode())
//                .put("Street", a.getStreet());
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object modifyAddress(String token, Address a,
//                                       Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        //noinspection SpellCheckingInspection
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("User.UpdateRecvAddress")
//                .put("AddressId", a.getId())
//                .put("AccessToken", token)
//                .put("Name", a.getName())
//                .put("Phone", a.getPhone())
//                .put("AreaCode", a.getAreaCode())
//                .put("Street", a.getStreet())
//                .put("IsDefault", a.isDefault() ? 1 : 0);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getAreas(Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        //noinspection SpellCheckingInspection
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Common.GetAreaList");
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getSignedPayOrder(String token, String orderId, int type,
//                                           Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        //noinspection SpellCheckingInspection
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Pay.AppOnlinePayRequest")
//                .put("AccessToken", token)
//                .put("PaytypeSysNo", type)
//                .put("OrderSysNo", orderId);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getFavorites(String token, Paging paging,
//                                      Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("member.QueryUserCollect")
//                .put("PageNo", paging.getNextPageIndexStartWith1())
//                .put("PageSize", paging.getPageSize())
//                .put("AccessToken", token)
//                .put("Type", 0);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object unfavorite(String token, ArrayList<FavoriteItem> items, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        @SuppressWarnings("SpellCheckingInspection")
//        JsonParamBuilder b = JsonParamBuilder.newInstance()
//                .setApiName("Member.CancleUserCollect")
//                .put("AccessToken", token);
//        JSONArray array = new JSONArray();
//        for (FavoriteItem item : items) {
//            array.put(Long.valueOf(item.getAutoId()));
//        }
//        b.put("SysNos", array);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getInsuranceList(String token, String orderId,
//                                          Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Member.QueryScreenInsuranceList")
//                .put("AccessToken", token)
//                .putOmitEmpty("OrderSysNo", orderId);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getInsuranceDetail(String token, String id,
//                                            Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("Member.QueryScreenInsurance")
//                .put("AccessToken", token)
//                .put("SysNo", id);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object removeBrowseHistories(String token, ArrayList<BrowsedItem> items, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        @SuppressWarnings("SpellCheckingInspection")
//        JsonParamBuilder b = JsonParamBuilder.newInstance()
//                .setApiName("Member.CancleUserCollect")
//                .put("AccessToken", token);
//        JSONArray array = new JSONArray();
//        for (BrowsedItem item : items) {
//            array.put(Long.valueOf(item.getAutoId()));
//        }
//        b.put("SysNos", array);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getBrowseHistories(String token, Paging paging,
//                                            Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("member.QueryUserCollect")
//                .put("PageNo", paging.getNextPageIndexStartWith1())
//                .put("PageSize", paging.getPageSize())
//                .put("AccessToken", token)
//                .put("Type", 2);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object feedback(String token, String content,
//                                  Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        JsonParamBuilder b = JsonParamBuilder.newInstance()
//                .setApiName("Member.AddUserFeedback")
//                .put("AccessToken", token)
//                .put("Content", content);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    @SuppressWarnings("unused")
//    public static Object verifyCaptcha(String key, String code,
//                                       Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("User.CheckVerifyCode")
//                .put("verifyKey", key)
//                .put("verifyCode", code);
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getHelpCategories(Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("CmsArticle.GetHelpCategoryTree");
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
//
//    public static Object getHelps(HelpCategory helpCategory, Paging paging, Response.Listener<JSONObject> l, Response.ErrorListener e) {
//        ParamBuilder b = ParamBuilder.newInstance()
//                .setApiName("CmsArticle.GetHelpCenterList")
//                .put("CategorySysNo", helpCategory.getId())
//                .put("PageNo", paging.getNextPageIndexStartWith1())
//                .put("PageSize", paging.getPageSize());
//        JsonObjectRequest r = new JsonObjectRequest(b.build(), l, e);
//        return RequestManager.add(r);
//    }
}
