package com.motaharinia.business.service.loguploadedfile;


public enum LogUploadedFsoEnum {
    //socialgroup:
    SOCIAL_GROUP_LOGO("/common/socialgroup/%ENTITYID%/logo/"),
    //membershiprequest:
    MEMBERSHIP_REQUEST_PERSONALITY("/common/membershiprequest/%ENTITYID%/personality/"),
    //contract:
    CONTRACT_CONTRACT("/eshop/contract/%ENTITYID%/contract/"),
    //vendor:
    VENDOR_QR_CODE("/eshop/vendor/%ENTITYID%/qrcode/"),
    //carriercompany:
    CARRIER_COMPANY_LOGO("/eshop/carriercompany/%ENTITYID%/logo/"),
    //carrier:
    CARRIER_POSTSHIPMENT("/eshop/carrier/%ENTITYID%/postshipmentfile/"),
    //department:
    DEPARTMENT_LEVEL1_IMAGE("/eshop/department/%ENTITYID%/level1image/"),
    DEPARTMENT_LEVEL3_GUIDE("/eshop/department/%ENTITYID%/level3guide/"),
    DEPARTMENT_LEVEL3_SLIDER("/eshop/department/%ENTITYID%/level3slider/"),
    DEPARTMENT_LEVEL3_THUMBNAIL_IMAGE("/eshop/department/%ENTITYID%/level3thumbnailimage/"),
    //productcategory:
    PRODUCT_CATEGORY_IMAGE("/eshop/productcategory/%ENTITYID%/image/"),
    //productrequest:
    PRODUCT_REQUEST_COVER("/eshop/productrequest/%ENTITYID%/cover/"),
    PRODUCT_REQUEST_SLIDER_FOR_ZOOM("/eshop/productrequest/%ENTITYID%/sliderforzoom/"),
    PRODUCT_REQUEST_SLIDER_NORMAL("/eshop/productrequest/%ENTITYID%/slidernormal/"),
    PRODUCT_REQUEST_IMAGE_3D("/eshop/productrequest/%ENTITYID%/image3d/"),
    PRODUCT_REQUEST_ATTACHMENT("/eshop/productrequest/%ENTITYID%/attachment/"),
    //producttyperequest:
    PRODUCT_TYPE_REQUEST_QRBARCODE("/eshop/producttyperequest/%ENTITYID%/qrbarcode/"),
    PRODUCT_TYPE_REQUEST_COVER("/eshop/producttyperequest/%ENTITYID%/cover/"),
    PRODUCT_TYPE_REQUEST_SLIDER_FOR_ZOOM("/eshop/producttyperequest/%ENTITYID%/sliderforzoom/"),
    PRODUCT_TYPE_REQUEST_SLIDER_NORMAL("/eshop/producttyperequest/%ENTITYID%/slidernormal/"),
    PRODUCT_TYPE_REQUEST_IMAGE_3D("/eshop/producttyperequest/%ENTITYID%/image3d/"),
    PRODUCT_TYPE_REQUEST_ATTACHMENT("/eshop/producttyperequest/%ENTITYID%/attachment/"),
    //product:
    PRODUCT_COVER("/eshop/product/%ENTITYID%/cover/"),
    PRODUCT_SLIDER_FOR_ZOOM("/eshop/product/%ENTITYID%/sliderforzoom/"),
    PRODUCT_SLIDER_NORMAL("/eshop/product/%ENTITYID%/slidernormal/"),
    PRODUCT_IMAGE_3D("/eshop/product/%ENTITYID%/image3d/"),
    PRODUCT_ATTACHMENT("/eshop/product/%ENTITYID%/attachment/"),
    //producttype:
    PRODUCT_TYPE_QRBARCODE("/eshop/producttype/%ENTITYID%/qrbarcode/"),
    PRODUCT_TYPE_COVER("/eshop/producttype/%ENTITYID%/cover/"),
    PRODUCT_TYPE_SLIDER_FOR_ZOOM("/eshop/producttype/%ENTITYID%/sliderforzoom/"),
    PRODUCT_TYPE_SLIDER_NORMAL("/eshop/producttype/%ENTITYID%/slidernormal/"),
    PRODUCT_TYPE_IMAGE_3D("/eshop/producttype/%ENTITYID%/image3d/"),
    PRODUCT_TYPE_ATTACHMENT("/eshop/producttype/%ENTITYID%/attachment/"),
    //paymentsystem:
    USER_GIFT_REQUEST_IMAGE("/eshop/usergiftrequest/%ENTITYID%/image/"),
    USER_CREDIT_REQUEST_IMAGE("/eshop/usercreditrequest/%ENTITYID%/image/"),
    CREDIT_FACILITY_REQUEST_IMAGE("/eshop/creditfacilityrequest/%ENTITYID%/image/"),
    CREDIT_REQUEST_IMAGE("/eshop/creditrequest/%ENTITYID%/image/"),
    //purchaseordercarriergroup:
    PURCHASE_ORDER_CARRIER_GROUP_DELIVERY_CONFIRM_FORM("/eshop/purchaseordercarriergroup/%ENTITYID%/deliveryconfirmform/"),
    //purchaseOrderReturn:
    PURCHASE_ORDER_RETURN_IMAGE("/eshop/purchaseorderreturn/%ENTITYID%/image/"),
    
    //purchaseOrderReturn:
    FACILITY_REQUEST_CONFIRM_IMAGE("/eshop/facilityrequest/%ENTITYID%/facilityconfirmimage/"),
    FACILITY_REQUEST_INITIAL_IMAGE("/eshop/facilityrequest/%ENTITYID%/initialconfirmimage/"),

    //saleSharingGroup:
    SALE_SHARING_GROUP_RESOURCE_SUPPLY_FORM("/eshop/salesharinggroup/%ENTITYID%/resourcesupplyform/"),
    SALE_SHARING_GROUP_SAVING_ACCOUNT_RESOURCE_SUPPLY_FORM("/eshop/salesharinggroup/%ENTITYID%/savingaccountform/"),

    //saleSharingGroupSchedule
    SALE_SHARING_GROUP_SCHEDULE_CHECKIN_ACCOUNT_FORM("/eshop/salesharinggroupschedule/%ENTITYID%/checkingaccountform/"),

    //cart
    CART_ATTACHMENT("/eshop/cart/%ENTITYID%/attachment/"),

    //purchaseorder
    PURCHASE_ORDER_ATTACHMENT("/eshop/purchaseorder/%ENTITYID%/attachment/"),

    //facilityrequest
    FACILITY_REQUEST_ATTACHMENT("/eshop/facilityrequest/%ENTITYID%/attachment/"),

    //change rev
    PURCHASE_ORDER_CODE_DUPLICATE("/eshop/purchaseorder/codeDuplicate/"),
    ;
    
    
    //Excel:
    //USEREQUALITY_DBVALIDATION_ERROR_MULTI_USER_FIND("/userequality/databaseValidationErrorMultiUserFindWorkbook.xlsx"),
    //USEREQUALITY_DBVALIDATION_ERROR_EQUALITY_ON_USERNAME_FIND("/userequality/databaseValidationErrorEqualityOnUsernameFindWorkbook.xlsx"),
    //USEREQUALITY_DBVALIDATION_ERROR_EQUALITY_ON_NATIONALCODE_FIND("/userequality/databaseValidationErrorEqualityOnNationalCodeFindWorkbook.xlsx");
    
    private final String value;

    private LogUploadedFsoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getEntityKindDirectoryPath(Integer entityId) {
        //example: "/eshop/product/27/image3dfile/"
        String entityKindDirectoryPath = this.getValue().replace("%ENTITYID%", entityId.toString());
        return entityKindDirectoryPath;
    }

    public String getEntityDirectoryPath() {
        Integer entityIdIndex = this.getValue().indexOf("%ENTITYID%");
        //example: "/eshop/product/"
        String entityDirectoryPath = this.getValue().substring(0, entityIdIndex);
        return entityDirectoryPath;
    }

    public String getKindDirectoryPath(Integer entityId) {
        //example: "/eshop/product/27/image3dfile/"
        String entityKindDirectoryPath = getEntityKindDirectoryPath(entityId);
        Integer entityIdIndex = this.getValue().indexOf("%ENTITYID%");
        //example: "/eshop/product/"
        String entityDirectoryPath = this.getValue().substring(0, entityIdIndex);
        //example: "/27/image3dfile/"
        String kindDirectoryPath = "/" + entityKindDirectoryPath.replace(entityDirectoryPath, "");
        return kindDirectoryPath;
    }

    public String getKindFolderName() {
        String[] tempArray = this.getValue().split("/", -1);
        if (tempArray.length > 1) {
            return tempArray[tempArray.length - 2];
        } else {
            return "";
        }
    }
}
