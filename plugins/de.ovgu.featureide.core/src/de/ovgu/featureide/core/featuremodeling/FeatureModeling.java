package de.ovgu.featureide.core.featuremodeling;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

import de.ovgu.featureide.core.builder.ComposerExtensionClass;

/**
 * 
 * ComposerExtensionClass for the Feature Modeling extension.
 * 
 * @author Jens Meinicke
 */
public class FeatureModeling extends ComposerExtensionClass {

	public FeatureModeling() {
		
	}

	public void performFullBuild(IFile config) {
		
	}

	@Override
	public void addCompiler(IProject project, String sourcePath,
			String configPath, String buildPath) {

	}
	
	@Override
	public boolean hasFeatureFolder() {
		return false;
	}
	
	@Override
	public boolean hasFeatureFolders() {
		return false;
	}

	@Override
	public boolean hasSourceFolder() {
		return false;
	}

}
