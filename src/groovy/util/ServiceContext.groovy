package util

/**
 * Created by KS115 on 3/9/16.
 */
class ServiceContext {
    String  userId
    String  userFullName
    String  mainRole
    String  emailId
    boolean forceUserToResetPassword
    Date    triggeredPasswordReset
    String  userAgent
    String  userDevice
    String  host


    @Override
    public String toString() {
        return "util.ServiceContext{" +
                "userId='" + userId + '\'' +
                ", userFullName='" + userFullName + '\'' +
                ", mainRole='" + mainRole + '\'' +
                ", emailId='" + emailId + '\'' +
                ", forceUserToResetPassword=" + forceUserToResetPassword +
                ", triggeredPasswordReset=" + triggeredPasswordReset +
                ", userAgent='" + userAgent + '\'' +
                ", userDevice='" + userDevice + '\'' +
                ", host='" + host + '\'' +
                '}';
    }
}
