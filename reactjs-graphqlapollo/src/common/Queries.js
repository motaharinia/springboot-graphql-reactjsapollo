import gql from "graphql-tag";

// کوئری درخواست ناتیف پیام
const BRIEF_MESSEAGES_SUBSCRIPTION = gql`
  subscription BriefMesseages($userId: Int!) {
    briefMesseages(userId: $userId) {
      notifyCounter
    }
  }
`;


export  {
    BRIEF_MESSEAGES_SUBSCRIPTION
};