package com.metadt.enrollment.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name="Course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="COURSES")
public class Course implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="ID",  unique=true, updatable = false, nullable=false)
    private Long id;
		
    @Column(name="CODE", unique=true, nullable=false, length=10)
    private String code;
    
    @Column(name="TITLE", nullable=false, length=50)
    private String title;
    
    @Column(name="DESCRIPTION", nullable=false, length=50)
    private String description;
    
    @Column(name="CREDITS", nullable=false, scale=3)
    private Integer credits;


	/** Primary key. */
    protected static final String PK = "id";
    
	public static String getPk() {
		return PK;
	}

    /**
     * Compares the key for this instance with another Course.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Course and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Course)) {
            return false;
        }
        Course that = (Course) other;
        Object myId = this.getId();
        Object yourId = that.getId();
        if (myId==null ? yourId!=null : !myId.equals(yourId)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Course.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Course)) return false;
        return this.equalKeys(other) && ((Course)other).equalKeys(this);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        int i;
        int result = 17;
        if (getCode() == null) {
            i = 0;
        } else {
            i = getCode().hashCode();
        }
        result = 37*result + i;
        return result;
    }

    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[ Course | ");
        sb.append("id=").append(getCode()).append(" ]");
        return sb.toString();
    }
    
    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    public String toStringFull() {
    	
    	StringBuffer sb = new StringBuffer("[ Course | ");
    	sb.append(getId()).append(" | ");
        sb.append(getCode()).append(" | ");
        sb.append(getTitle()).append(" | ");
        sb.append(getDescription()).append(" | ");
        sb.append(getCredits()).append(" ]");
        return sb.toString();
    }

    /**
     * Return all elements of the primary key.
     *
     * @return Map of key names to values
     */
    public Map<String, Object> getPrimaryKey() {
        Map<String, Object> ret = new LinkedHashMap<String, Object>(1);
        ret.put("id", getId());
        return ret;
    }

}