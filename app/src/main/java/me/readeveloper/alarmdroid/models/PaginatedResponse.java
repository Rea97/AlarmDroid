package me.readeveloper.alarmdroid.models;

public class PaginatedResponse extends Model {
    private Model[] data;
    private LinksObjectResponse links;
    private MetaObjectResponse meta;

    public Model[] getData() {
        return this.data;
    }

    public void setData(Model[] data) {
        this.data = data;
    }

    public LinksObjectResponse getLinks() {
        return this.links;
    }

    public void setLinks(LinksObjectResponse links) {
        this.links = links;
    }

    public MetaObjectResponse getMeta() {
        return this.meta;
    }

    public void setMeta(MetaObjectResponse meta) {
        this.meta = meta;
    }
}
