package eu.caffeineabusers.signaturdigitale.subject;

import java.util.UUID;

/**
 * This class represents a subject. A subject is a person or an organization.
 *
 * @author Tomáš Plánský
 */
public record Subject(UUID uid, String firstName, String lastName, String publicKey) {



}
