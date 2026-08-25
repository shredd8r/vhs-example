package com.example.app.demo;

import com.example.app.common.BaseEntity;
import com.example.app.file.File;
import com.example.app.user.User;
import com.example.app.user.UserRole;
import io.vhs.meta.annotation.ui.*;
import io.vhs.meta.annotation.vaadin.VaadinField;
import io.vhs.ui.component.field.CheckboxGroupField;
import io.vhs.ui.component.field.RadioButtonGroupField;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.*;
import java.util.*;

@Getter
@Setter
@Title("Демо объект")
@Table(name = "DEMO_ENTITIES")
@Entity(name = "vhs_DemoEntity")
public class DemoEntity extends BaseEntity {

    private boolean booleanPrimitive;
    private char charPrimitive;
    private byte bytePrimitive;
    private short shortPrimitive;
    private int intPrimitive;
    private long longPrimitive;
    private float floatPrimitive;
    private double doublePrimitive;

    private Boolean booleanValue;
    private Character charValue;
    private Byte byteValue;
    private Short shortValue;
    private Integer intValue;
    private Long longValue;
    private Float floatValue;
    private Double doubleValue;

    private BigDecimal bigDecimal;
    private BigInteger bigInteger;

    private String string;
    @Mask("+{7} (000) 000-00-00")
    private String phone;
    @Password
    private String password;
    @Column(length = 1000)
    private String text;
    @Column(length = 2000)
    private String largeText;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String lob;
    @Email
    private String email;
    @Url
    private String url;

    @Temporal(TemporalType.DATE)
    private Date date;
    @Temporal(TemporalType.TIME)
    private Date time;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Temporal(TemporalType.DATE)
    private Calendar dateCalendar;
    @Temporal(TemporalType.TIME)
    private Calendar timeCalendar;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar timestampCalendar;

    private Instant instant;
    private LocalDate localDate;
    private LocalTime localTime;
    private LocalDateTime localDateTime;
    private OffsetDateTime offsetDateTime;
    private OffsetTime offsetTime;
    private ZonedDateTime zonedDateTime;

    private Year year;
    private YearMonth yearMonth;
    private Month month;
    private MonthDay monthDay;
    private DayOfWeek dayOfWeek;

    private UUID uuid;
    private Period period;
    private Duration duration;
    private ZoneId zoneId;

    @VaadinField(RadioButtonGroupField.class)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @VaadinField(CheckboxGroupField.class)
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.LAZY)
    private Set<UserRole> roles;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private File file;

    @OneToMany(fetch = FetchType.LAZY)
    @GridColumns({"name", "username"})
    private Set<User> users;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<File> files;

    @ElementCollection(targetClass = String.class)
    private Set<@Email String> emails;

    @MapKeyClass(String.class)
    @ElementCollection(targetClass = Integer.class)
    private Map<@Placeholder("String") String, @Placeholder("Integer") Integer> map;

    @Deprecated
    private String deprecated;

    @Embedded
    private EmbeddableObject embedded;

    @Getter
    @Setter
    @Embeddable
    public static class EmbeddableObject {

        private String string;
        private Integer integer;
    }
}
