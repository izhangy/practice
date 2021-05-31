package cmos.stream;

/**
 * @author When all else is lost the future still remains.
 * @date 2021/5/19 - 10:10
 **/
public class BeanObj {
    private String campaignName;
    private String campaignId;
    private String serviceTypeId;
    private String resource;
    private String actPrio;
    private Integer priority;

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(String serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getActPrio() {
        return actPrio;
    }

    public void setActPrio(String actPrio) {
        this.actPrio = actPrio;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }


    @Override
    public String toString() {
        return "BeanObj{" +
                "campaignName='" + campaignName + '\'' +
                ", campaignId='" + campaignId + '\'' +
                ", serviceTypeId='" + serviceTypeId + '\'' +
                ", resource='" + resource + '\'' +
                ", actPrio='" + actPrio + '\'' +
                ", priority=" + priority +
                '}';
    }
}
