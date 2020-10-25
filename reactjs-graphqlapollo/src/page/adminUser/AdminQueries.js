import gql from "graphql-tag";

// کوئری درخواست نمایش اطلاعات گرید
const ADMIN_USER_READ_GRID = gql `
    query {
  readGridByModel(
    searchFilterModel: {}
  ) {
    page
    records
    searchDataColModelList {
      name
    }
    searchDataRowModelList {
      id
      rowCellArray
    }
  }
}
`;

// کوئری درخواست ثبت ادمین جدید
const ADMIN_USER_CREATE_MUTATION = gql `
    mutation Create(
    $username:String!,
    $firstName:String,
    $lastName:String,
    $defaultAdminUserContact_address:String,
    $skillList:[AdminUserSkillModelInput],
    $gender_id:Int!,
    $defaultAdminUserContact_city_id:Int!
    
    ) {
  create(adminUserModel:{
  username:$username,
  firstName:$firstName,
  lastName:$lastName,
  defaultAdminUserContact_address:$defaultAdminUserContact_address,
  skillList:$skillList,
  defaultAdminUserContact_city_id:$defaultAdminUserContact_city_id,
  gender_id:$gender_id
  }) {
    id
  }
}
`;

// کوئری درخواست اطلاعات ادمین با ایدی مورد نظر
const ADMIN_USER_READ_GRID_BY_ID = gql `
query  common_adminUser_readById($id:Int!){
    common_adminUser_readById(id: $id) {
        username,
        firstName,
        lastName,
        gender_id,
        defaultAdminUserContact_city_id,
        defaultAdminUserContact_address,
        id
    }
}
`;

// کوئری درخواست ویرایش اطلاعات ادمین
const ADMIN_USER_UPDATE_MUTATION = gql `
    mutation Update(
    $username:String!,
    $firstName:String,
    $lastName:String,
    $defaultAdminUserContact_address:String,
    $skillList:[AdminUserSkillModelInput],
    $gender_id:Int!,
    $defaultAdminUserContact_city_id:Int!,
    $id:Int!
    ) {
  update(adminUserModel:{
  username:$username,
  firstName:$firstName,
  lastName:$lastName,
  defaultAdminUserContact_address:$defaultAdminUserContact_address,
  skillList:$skillList,
    defaultAdminUserContact_city_id:$defaultAdminUserContact_city_id,
  gender_id:$gender_id,
  id:$id
  }) {
    id,
    username,
    firstName,
    lastName,
    gender_id,
  }
}
`;

// کوئری درخواست حذف ادمین
const ADMIN_USER_DELETE_MUTATION = gql `
  mutation Delete($id:Int!) 
   {
      delete( id:$id)
      {
        id
      }
   }
`;


export  {
    ADMIN_USER_READ_GRID,
    ADMIN_USER_READ_GRID_BY_ID,
    ADMIN_USER_CREATE_MUTATION,
    ADMIN_USER_UPDATE_MUTATION,
    ADMIN_USER_DELETE_MUTATION

};