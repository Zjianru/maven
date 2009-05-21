package org.apache.maven.execution;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.repository.ArtifactRepositoryPolicy;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.Profile;
import org.apache.maven.project.ProjectBuilderConfiguration;
import org.apache.maven.settings.Settings;
import org.apache.maven.wagon.events.TransferListener;
import org.codehaus.plexus.logging.Logger;

/**
 * @author Jason van Zyl
 * @version $Id$
 */
public interface MavenExecutionRequest
{
    // ----------------------------------------------------------------------
    // Logging
    // ----------------------------------------------------------------------

    static final int LOGGING_LEVEL_DEBUG = Logger.LEVEL_DEBUG;

    static final int LOGGING_LEVEL_INFO = Logger.LEVEL_INFO;

    static final int LOGGING_LEVEL_WARN = Logger.LEVEL_WARN;

    static final int LOGGING_LEVEL_ERROR = Logger.LEVEL_ERROR;

    static final int LOGGING_LEVEL_FATAL = Logger.LEVEL_FATAL;

    static final int LOGGING_LEVEL_DISABLED = Logger.LEVEL_DISABLED;

    // ----------------------------------------------------------------------
    // Reactor Failure Mode
    // ----------------------------------------------------------------------

    static final String REACTOR_FAIL_FAST = "FAIL_FAST";    

    static final String REACTOR_FAIL_AT_END = "FAIL_AT_END";

    static final String REACTOR_FAIL_NEVER = "FAIL_NEVER";

    // ----------------------------------------------------------------------
    // Artifactr repository policies
    // ----------------------------------------------------------------------

    static final String CHECKSUM_POLICY_FAIL = ArtifactRepositoryPolicy.CHECKSUM_POLICY_FAIL;

    static final String CHECKSUM_POLICY_WARN = ArtifactRepositoryPolicy.CHECKSUM_POLICY_WARN;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    // Base directory
    MavenExecutionRequest setBaseDirectory( File basedir );
    String getBaseDirectory();

    // Timing (remove this)
    MavenExecutionRequest setStartTime( Date start );
    Date getStartTime();

    // Goals
    MavenExecutionRequest setGoals( List<String> goals );
    List<String> getGoals();

    // Properties
    MavenExecutionRequest setProperties( Properties properties );
    MavenExecutionRequest setProperty( String key, String value );
    Properties getProperties();

    // Reactor
    MavenExecutionRequest setReactorFailureBehavior( String failureBehavior );
    String getReactorFailureBehavior();

    // Recursive (really to just process the top-level POM)
    MavenExecutionRequest setRecursive( boolean recursive );
    boolean isRecursive();

    MavenExecutionRequest setPom( File pom );
    File getPom();

    // Errors
    MavenExecutionRequest setShowErrors( boolean showErrors );
    boolean isShowErrors();

    // Transfer listeners
    MavenExecutionRequest setTransferListener( TransferListener transferListener );
    TransferListener getTransferListener();

    // Logging
    MavenExecutionRequest setLoggingLevel( int loggingLevel );
    int getLoggingLevel();

    // Update snapshots
    MavenExecutionRequest setUpdateSnapshots( boolean updateSnapshots );
    boolean isUpdateSnapshots();

    MavenExecutionRequest setNoSnapshotUpdates( boolean noSnapshotUpdates );
    boolean isNoSnapshotUpdates();

    // Checksum policy
    MavenExecutionRequest setGlobalChecksumPolicy( String globalChecksumPolicy );
    String getGlobalChecksumPolicy();

    // Local repository
    MavenExecutionRequest setLocalRepositoryPath( String localRepository );
    MavenExecutionRequest setLocalRepositoryPath( File localRepository );
    File getLocalRepositoryPath();
    MavenExecutionRequest setLocalRepository( ArtifactRepository repository );
    ArtifactRepository getLocalRepository();

    // Interactive
    MavenExecutionRequest setInteractiveMode( boolean interactive );
    boolean isInteractiveMode();

    // Offline
    MavenExecutionRequest setOffline( boolean offline );
    boolean isOffline();

    // Profiles
    List<Profile> getProfiles();
    MavenExecutionRequest addProfile( Profile profile );
    MavenExecutionRequest setProfiles( List<Profile> profiles );
    MavenExecutionRequest addActiveProfile( String profile );
    MavenExecutionRequest addActiveProfiles( List<String> profiles );
    List<String> getActiveProfiles();
    MavenExecutionRequest addInactiveProfile( String profile );
    MavenExecutionRequest addInactiveProfiles( List<String> profiles );
    List<String> getInactiveProfiles();

    // Proxies
    List getProxies();
    MavenExecutionRequest setProxies( List proxies );

    // Servers
    List getServers();
    MavenExecutionRequest setServers( List servers );

    // Mirrors
    List getMirrors();
    MavenExecutionRequest setMirrors( List mirrors );

    // Plugin groups
    List<String> getPluginGroups();
    MavenExecutionRequest setPluginGroups( List<String> pluginGroups );
    MavenExecutionRequest addPluginGroup( String pluginGroup );

    boolean isUsePluginUpdateOverride();
    MavenExecutionRequest setUsePluginUpdateOverride( boolean usePluginUpdateOverride );

    // Setting
    Settings getSettings();
    MavenExecutionRequest setSettings( Settings settings );

    boolean isProjectPresent();
    MavenExecutionRequest setProjectPresent( boolean isProjectPresent );

    File getUserSettingsFile();
    MavenExecutionRequest setUserSettingsFile( File userSettingsFile );

    File getGlobalSettingsFile();
    MavenExecutionRequest setGlobalSettingsFile( File globalSettingsFile );

    MavenExecutionRequest addRemoteRepository( ArtifactRepository repository );
    /**
     * Set a new list of remote repositories to use the execution request. This is necessary if you perform
     * transformations on the remote repositories being used. For example if you replace existing repositories with
     * mirrors then it's easier to just replace the whole list with a new list of transformed repositories.
     * 
     * @param repositories
     * @return
     */
    MavenExecutionRequest setRemoteRepositories( List<ArtifactRepository> repositories );
    List<ArtifactRepository> getRemoteRepositories();

    File getUserToolchainsFile();
    MavenExecutionRequest setUserToolchainsFile( File userToolchainsFile );

    ProjectBuilderConfiguration getProjectBuildingConfiguration();    
}
