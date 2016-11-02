package com.redhat.red.build.koji.model.xmlrpc;

import org.commonjava.maven.atlas.ident.ref.ProjectRef;
import org.commonjava.maven.atlas.ident.ref.ProjectVersionRef;
import org.commonjava.maven.atlas.ident.ref.SimpleProjectRef;
import org.commonjava.maven.atlas.ident.ref.SimpleProjectVersionRef;
import org.commonjava.rwx.binding.anno.DataKey;
import org.commonjava.rwx.binding.anno.StructPart;

import static com.redhat.red.build.koji.model.json.KojiJsonConstants.ARTIFACT_ID;
import static com.redhat.red.build.koji.model.json.KojiJsonConstants.GROUP_ID;
import static com.redhat.red.build.koji.model.json.KojiJsonConstants.VERSION;

/**
 * Created by jdcasey on 11/1/16.
 */
@StructPart
public class KojiMavenRef
{
    @DataKey( GROUP_ID )
    private String groupId;

    @DataKey( ARTIFACT_ID )
    private String artifactId;

    @DataKey( VERSION )
    private String version;

    public KojiMavenRef()
    {
    }

    public KojiMavenRef( ProjectRef ref )
    {
        this.groupId = ref.getGroupId();
        this.artifactId = ref.getArtifactId();

        if ( ref instanceof ProjectVersionRef )
        {
            this.version = ( (ProjectVersionRef) ref ).getVersionString();
        }
    }

    public KojiMavenRef( String groupId, String artifactId, String version )
    {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }

    public String getGroupId()
    {
        return groupId;
    }

    public String getArtifactId()
    {
        return artifactId;
    }

    public String getVersion()
    {
        return version;
    }

    public void setGroupId( String groupId )
    {
        this.groupId = groupId;
    }

    public void setArtifactId( String artifactId )
    {
        this.artifactId = artifactId;
    }

    public void setVersion( String version )
    {
        this.version = version;
    }

    public KojiMavenRef withGroupId( String groupId )
    {
        this.groupId = groupId;
        return this;
    }

    public KojiMavenRef withArtifactId( String artifactId )
    {
        this.artifactId = artifactId;
        return this;
    }

    public KojiMavenRef withVersion( String version )
    {
        this.version = version;
        return this;
    }

    public ProjectVersionRef toGAV()
    {
        if ( groupId == null || artifactId == null || version == null )
        {
            return null;
        }

        return new SimpleProjectVersionRef( groupId, artifactId, version );
    }

    public ProjectRef toGA()
    {
        if ( groupId == null || artifactId == null )
        {
            return null;
        }

        if ( version == null )
        {
            return new SimpleProjectRef( groupId, artifactId );
        }

        return new SimpleProjectVersionRef( groupId, artifactId, version );
    }
}
