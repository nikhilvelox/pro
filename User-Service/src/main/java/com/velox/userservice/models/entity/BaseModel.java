    package com.velox.userservice.models.entity;

    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;
    import org.hibernate.annotations.CreationTimestamp;
    import org.hibernate.annotations.UpdateTimestamp;
    import java.time.LocalDateTime;
    import java.util.Objects;
    import java.util.UUID;

    @Getter @Setter @MappedSuperclass
    public abstract class BaseModel {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        @Column(updatable = false,nullable = false)
        private UUID id;
        @Version // optimistic locking
        private Long version;
        @CreationTimestamp @Column(updatable = false,nullable = false) private  LocalDateTime creationTimeStamp;
        @UpdateTimestamp @Column(nullable = false) private  LocalDateTime updationTimeStamp;
        @Column(nullable = false)
        private boolean deleted = false; // Built-in support for global Soft Deletes

        @Override
        public boolean equals(Object o){
         if(this == o)return true;
         if(o == null || getClass() != o.getClass())return false;
         BaseModel baseModel = (BaseModel)o;
            return id != null && Objects.equals(id, baseModel.id);
        }

        @Override
        public int hashCode() {
            return getClass().hashCode(); // Consistent hash code before and after entity persist
        }
    }
