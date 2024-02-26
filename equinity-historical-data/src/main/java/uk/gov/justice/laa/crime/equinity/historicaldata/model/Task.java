package uk.gov.justice.laa.crime.equinity.historicaldata.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "Tasks")
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @Getter
    private long ID;

    @Column(name="Type", insertable=false, updatable=false)
    @Getter
    private Integer typeId;

    @Column(name="State", insertable=false, updatable=false)
    @Getter
    private Integer stateId;

    @Getter
    private Integer OriginatorID;

    @Column(name = "DTOriginated")
    @Getter
    private LocalDateTime DTOriginated;

    @Getter
    private Integer CurrentParticipantID;

    @Getter
    private LocalDateTime DTLastSent;

    @Getter
    private String UF1Text;

    @Getter
    private String UF2Text;

    @Getter
    private String UF3Text;

    @Getter
    private Integer UF3Number;

    @Getter
    private String UF4Text;

    @Getter
    private String UF5Text;

    @Getter
    private String UF6Text;

    @Getter
    private Integer UF6Number;

    @ManyToOne
    @JoinColumn(name = "Type", insertable = false, updatable = false)
    @Getter
    private TaskType taskType;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="Type", referencedColumnName="TaskTypeID"),
        @JoinColumn(name="State", referencedColumnName="StateID"),
    })
    @Getter
    private TaskTypeState taskState;

    @ManyToOne
    @JoinColumn(name = "OriginatorID", insertable = false, updatable = false)
    @Getter
    private Participant originator;

    @ManyToOne
    @JoinColumn(name = "CurrentParticipantID", insertable = false, updatable = false)
    @Getter
    private Participant currentParticipant;

    @ManyToOne
    @JoinColumn(name = "UF3Number", insertable=false, updatable=false)
    @Getter
    private ParentTask parentTask;

    @Column(name = "OFDImage")
    @Setter
    private byte[] crmFile;

    public byte[] exportCrmFile() {
        return crmFile;
    }
}
