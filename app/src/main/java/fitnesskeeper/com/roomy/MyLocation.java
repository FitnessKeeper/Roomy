package fitnesskeeper.com.roomy;

/**
 * @author stuckj, created on 6/30/17.
 */

class MyLocation
{
    private Double latitude;
    private Double longitude;

    public MyLocation(final Double latitude, final Double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(Double latitude)
    {
        this.latitude = latitude;
    }

    public Double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(Double longitude)
    {
        this.longitude = longitude;
    }

    @Override
    public String toString()
    {
        return String.format("[%f,%f]", latitude, longitude);
    }
}