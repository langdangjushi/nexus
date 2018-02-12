package com.chinapex.nexus.model;

import com.chinapex.nexus.exception.ValueNotUniqueException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

/**
 * 统一配置 Model
 * created by pengmingguo on 2/11/18
 */
@Entity
@Table(name = "t_config")
@Getter
@Setter
public class Config {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String value;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Config parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Config> children;


    ///////////////////////////////////////
    //  util method          /////////////

    public String value(String key) {
        Config config = checkUnique(key);
        return config == null ? null : config.value;
    }

    public Collection<String> values(String key) {
        if (isEmpty(children))
            return new ArrayList<>();
        return findConfig(key).stream().map(e -> e.value).collect(Collectors.toList());
    }

    public Config child(String key) {
        return checkUnique(key);
    }

    private Config checkUnique(String key) {
        if (isEmpty(children))
            return null;
        List<Config> values = findConfig(key);
        if (values.size() != 1)
            throw new ValueNotUniqueException("value of " + name + " not unique");
        return values.get(0);
    }

    private List<Config> findConfig(String key) {
        return children.stream().filter(e -> key.equals(e.name)).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Config config = (Config) o;
        return Objects.equals(id, config.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}