package com.hotel.crescent.model.auth;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

public abstract class AuthInfo {
	
	public AuthInfo() {
		
	}
	
	private String role;
	private String subject;
	private String type;

	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public abstract String getSubject();
	public abstract String getType();
	
	public static class AuthInfoDeserializer extends StdDeserializer<AuthInfo> {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public AuthInfoDeserializer() {
            this(null);
        }

        public AuthInfoDeserializer(final Class<?> vc) {
            super(vc);
        }
        
        ObjectMapper mapper = new ObjectMapper();

        @Override
        public AuthInfo deserialize(final JsonParser parser, final DeserializationContext context)
        throws IOException {
            final JsonNode node = parser.getCodec().readTree(parser);
            final ObjectMapper mapper = (ObjectMapper)parser.getCodec();
            if (node.get("type").asText().equals("UserAuthInfo")) {
                return mapper.treeToValue(node, UserAuthInfo.class);
            }
            return null;
        }
    }
	
	 public static ObjectMapper getMapper() {
		 ObjectMapper mapper = new ObjectMapper();
         SimpleModule module = new SimpleModule();
         module.addDeserializer(AuthInfo.class, new AuthInfoDeserializer());
         mapper.registerModule(module);
         return mapper;
     }
}
