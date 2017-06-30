package fitnesskeeper.com.roomy;

import android.arch.persistence.room.ColumnInfo;

/**
 * @author stuckj, created on 6/29/17.
 */

public class UserName
{
    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
}